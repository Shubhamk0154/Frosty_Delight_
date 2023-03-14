package com.app.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.CustomException.ResourceNotFoundException;
import com.app.dto.CartResponse;
import com.app.pojos.CartItem;
import com.app.pojos.Product;
import com.app.pojos.ShoppingCart;
import com.app.pojos.User;
import com.app.repository.ShoppingCartRepository;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
 
	@Autowired
    private ShoppingCartRepository cartRepository;
	@Autowired
    private ProductService productService;
	@Autowired
    private com.app.service.userService userService;
	@Autowired
	private ModelMapper mapper;




    @Override
    public ShoppingCart addToShoppingCart(Long id, Integer amount) {
        User user = userService.getUser();
        ShoppingCart cart = user.getCart();

        if (Objects.nonNull(cart) && Objects.nonNull(cart.getCartItems()) && !cart.getCartItems().isEmpty()) {
            Optional<CartItem> cartItem = cart.getCartItems()
                    .stream()
                    .filter(ci -> ci.getCartProduct().getId().equals(id)).findFirst();
            if (cartItem.isPresent()) {
                if (cartItem.get().getCartProduct().getInStock()==false) {
                    throw new IllegalArgumentException("Product does not have desired stock.");
                }
                cartItem.get().setQuantity(cartItem.get().getQuantity()+amount);
                cart.setTotalItems(cart.getTotalItems()+amount);
                ShoppingCart updatedCart = calculatePrice(cart);
                //updatedCart.setTotalItems(updatedCart.getTotalItems()+amount);
                return cartRepository.save(updatedCart);
            }
        }

        if (Objects.isNull(cart)) {
            cart = createCart(user);
        }

        Product product = productService.findproductById(id);

        if (product.getInStock()==false) {
            throw new IllegalArgumentException("Product does not have desired stock.");
        }

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(amount);
        cartItem.setCartProduct(product);
        cartItem.setCart(cart);
        cartItem.setTotalPrice(product.getPrice()*amount);

        if (Objects.isNull(cart.getCartItems())) {
            cart.setCartItems(null);
        }
        cart.getCartItems().add(cartItem);
        cart.setTotalItems(amount);
        cart = calculatePrice(cart);

        cart = cartRepository.save(cart);
        return cart;
    }
    
    @Override
    public ShoppingCart calculatePrice(ShoppingCart cart) {
        cart.setTotalCartPrice(0F);

        cart.getCartItems().forEach(cartItem -> {
            cart.setTotalCartPrice(cart.getTotalCartPrice() + (cartItem.getCartProduct().getPrice()) * cartItem.getQuantity());
        });

        cart.setTotalCartPrice(cart.getTotalCartPrice());
        return cart;
    }
    
    @Override
    public CartResponse fetchCart() {
        ShoppingCart cart = userService.getUser().getCart();
        if (cart == null) {
            return null;
        }
        return mapper.map(cart,CartResponse.class);
    }

    @Override
    public ShoppingCart removeFromCart(Long cartItemId) {
        User user = userService.getUser();
        ShoppingCart cart = user.getCart();

        if (Objects.isNull(cart) || Objects.isNull(cart.getCartItems()) || cart.getCartItems().isEmpty()) {
            throw new ResourceNotFoundException("Cart or CartItem not found");
        }

        List<CartItem> cartItemsList = cart.getCartItems();
        Optional<CartItem> cartItem = cart.getCartItems()
                .stream()
                .filter(ci -> ci.getId().equals(cartItemId)).findFirst();
        if (cartItem.isEmpty()) {
            throw new ResourceNotFoundException("CartItem not found");
        }

        cartItemsList.remove(cartItem.get());

        if (Objects.isNull(cart.getCartItems()) || cart.getCartItems().isEmpty()) {
            user.setCart(null);
            userService.saveUser(user);
            return null;
        }

        cart.setCartItems(cartItemsList);
        cart = calculatePrice(cart);
        return cartRepository.save(cart);
       // return cartResponseConverter.apply(cart);
    }
    
    @Override
    public void emptyCart() {
        User user = userService.getUser();
        user.setCart(null);
        userService.saveUser(user);
    }

//    @Override
//    public CartResponse incrementCartItem(Long cartItemId, Integer amount) {
//        User user = userService.getUser();
//        Cart cart = user.getCart();
//        if (Objects.isNull(cart) || Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
//            throw new ResourceNotFoundException("Empty cart");
//        }
//
//        CartItem cartItem = cart.getCartItemList()
//                .stream()
//                .filter(ci -> ci.getId().equals(cartItemId))
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found"));
//
//        if (cartItem.getCartProduct().getStock() < (cartItem.getAmount() + amount)) {
//            throw new InvalidArgumentException("Product does not have desired stock.");
//        }
//
//        cartItem.setAmount(cartItem.getAmount() + amount);
//        cart = calculatePrice(cart);
//        cart = cartRepository.save(cart);
//        return cartResponseConverter.apply(cart);
//    }
//
//    @Override
//    public CartResponse decrementCartItem(Long cartItemId, Integer amount) {
//        User user = userService.getUser();
//        Cart cart = user.getCart();
//        if (Objects.isNull(cart) || Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
//            throw new ResourceNotFoundException("Empty cart");
//        }
//
//        CartItem cartItem = cart.getCartItemList()
//                .stream()
//                .filter(ci -> ci.getId().equals(cartItemId))
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found"));
//
//
//        if (cartItem.getAmount() <= amount) {
//            List<CartItem> cartItemList = cart.getCartItemList();
//            cartItemList.remove(cartItem);
//            if (Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
//                user.setCart(null);
//                userService.saveUser(user);
//                return null;
//            }
//            cart.setCartItemList(cartItemList);
//            cart = calculatePrice(cart);
//            cart = cartRepository.save(cart);
//            return cartResponseConverter.apply(cart);
//        }
//
//        cartItem.setAmount(cartItem.getAmount() - amount);
//        cart = calculatePrice(cart);
//        cart = cartRepository.save(cart);
//        return cartResponseConverter.apply(cart);
//    }
//
   
//
//    @Override
//    public boolean confirmCart(ConfirmCartRequest confirmCartRequest) {
//        Cart dbCart = userService.getUser().getCart();
//        if (Objects.isNull(dbCart)) {
//            return false;
//        }
//        List<CartItem> dbCartItemsList = dbCart.getCartItemList();
//        List<CartItemDTO> cartItemsList = confirmCartRequest.getCartItems();
//        if (dbCartItemsList.size() != cartItemsList.size()) {
//            return false;
//        }
//
//        for (int i = 0; i < dbCartItemsList.size(); i++) {
//            if (!dbCartItemsList.get(i).getId().equals(cartItemsList.get(i).getId()) &&
//                    !dbCartItemsList.get(i).getAmount().equals(cartItemsList.get(i).getAmount()) &&
//                    !dbCartItemsList.get(i).getCartProduct().getId().equals(cartItemsList.get(i).getId())) {
//                return false;
//            }
//        }
//
//        if (dbCart.getTotalPrice().equals(confirmCartRequest.getTotalPrice()) &&
//                dbCart.getTotalCargoPrice().equals(confirmCartRequest.getTotalCargoPrice()) &&
//                dbCart.getTotalCartPrice().equals(confirmCartRequest.getTotalCartPrice())) {
//            if (Objects.nonNull(dbCart.getDiscount()) && Objects.nonNull(confirmCartRequest.getDiscount())) {
//                return dbCart.getDiscount().getDiscountPercent().equals(confirmCartRequest.getDiscount().getDiscountPercent());
//            }
//            return Objects.isNull(dbCart.getDiscount()) && Objects.isNull(confirmCartRequest.getDiscount());
//        }
//        return false;
//    }
//
    
//
//    @Override
//    public Cart getCart() {
//        return userService.getUser().getCart();
//    }
//
//
//    @Override
//    public void saveCart(Cart cart) {
//        if (Objects.isNull(cart)) {
//            throw new InvalidArgumentException("Cart is null");
//        }
//        cartRepository.save(cart);
//    }

  

//    private float roundTwoDecimals(float d) {
//        DecimalFormat twoDForm = new DecimalFormat("#.##");
//        return Float.parseFloat(twoDForm.format(d));
//    }

    private ShoppingCart createCart(User user) {
        ShoppingCart cart = new ShoppingCart();
        cart.setCustomer(user);
        return cart;
    }
}
