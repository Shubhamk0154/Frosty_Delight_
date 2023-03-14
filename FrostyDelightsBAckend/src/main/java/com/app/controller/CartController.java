package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.AddToCartRequest;
import com.app.dto.CartResponse;
import com.app.dto.RemoveFromCartRequest;
import com.app.pojos.ShoppingCart;
import com.app.service.ShoppingCartService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cart")
public class CartController {
    
	@Autowired
    private  ShoppingCartService cartService;


    @PostMapping(value = "/usercart")
    public ResponseEntity<ShoppingCart> addToCart(@RequestBody @Valid AddToCartRequest addToCartRequest) {
        ShoppingCart cart = cartService.addToShoppingCart(addToCartRequest.getProductId(), addToCartRequest.getAmount());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

//    @PostMapping(value = "/cart/increment")
//    public ResponseEntity<CartResponse> increaseCartItem(@RequestBody @Valid IncrementCartItemRequest incrementCartItemRequest) {
//        CartResponse cartResponse = cartService.incrementCartItem(incrementCartItemRequest.getCartItemId(), incrementCartItemRequest.getAmount());
//        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
//    }

//    @PostMapping(value = "/cart/decrement")
//    public ResponseEntity<CartResponse> decreaseCartItem(@RequestBody @Valid DecrementCartItemRequest decrementCartItemRequest) {
//        CartResponse cartResponse = cartService.decrementCartItem(decrementCartItemRequest.getCartItemId(), decrementCartItemRequest.getAmount());
//        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
//    }
//
    @GetMapping
    public ResponseEntity<CartResponse> fetchCart() {
        CartResponse cartResponse = cartService.fetchCart();
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/cart/remove")
    public ResponseEntity<ShoppingCart> removeFromCart(@RequestBody @Valid RemoveFromCartRequest removeFromCartRequest) {
        ShoppingCart cartResponse = cartService.removeFromCart(removeFromCartRequest.getCartItemId());
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cart")
    public ResponseEntity<HttpStatus> emptyCart() {
        cartService.emptyCart();
        return new ResponseEntity<>(HttpStatus.OK);
    }
//
//    @PostMapping(value = "/cart/confirm")
//    public ResponseEntity<Boolean> confirmCart(@RequestBody @Valid ConfirmCartRequest confirmCartRequest) {
//        Boolean confirmCart = cartService.confirmCart(confirmCartRequest);
//        return new ResponseEntity<>(confirmCart, HttpStatus.OK);
//    }

}
