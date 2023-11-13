package com.github.shop.domain.cart;

public class CartNotFoundException extends Exception {
    public CartNotFoundException() {
        super("Cart not found");
    }
}
