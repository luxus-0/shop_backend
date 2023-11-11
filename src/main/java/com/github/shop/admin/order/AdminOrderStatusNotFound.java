package com.github.shop.admin.order;

public class AdminOrderStatusNotFound extends Exception {
    public AdminOrderStatusNotFound() {
        super("Undefined order status");
    }
}
