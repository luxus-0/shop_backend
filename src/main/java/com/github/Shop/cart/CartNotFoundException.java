package com.github.Shop.cart;

public class CartNotFoundException extends Exception {
    public CartNotFoundException() {
        super("Cart not found");
    }
}
