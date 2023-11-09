package com.github.shop.cart;

public class CartNotFoundException extends Exception {
    public CartNotFoundException() {
        super("Cart not found");
    }
}
