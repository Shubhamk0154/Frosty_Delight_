package com.app.service;

import com.app.dto.CartResponse;
import com.app.pojos.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart addToShoppingCart(Long id, Integer amount);

//    CartResponse incrementShoppingCartItem(Long CartItemId, Integer amount);
//
//    CartResponse decrementShoppingCartItem(Long CartItemId, Integer amount);
//
//    CartResponse fetchShoppingCart();
//
//    CartResponse removeFromShoppingCart(Long id);
//
//  //  boolean confirmShoppingCart(ConfirmCartRequest confirmCartRequest);
//
//    ShoppingCart getShoppingCart();
//
//    void saveShoppingCart(ShoppingCart ShoppingCart);
//
//    void emptyShoppingCart();

    ShoppingCart calculatePrice(ShoppingCart ShoppingCart);

	ShoppingCart removeFromCart(Long cartItemId);

	CartResponse fetchCart();

	void emptyCart();

	

}
