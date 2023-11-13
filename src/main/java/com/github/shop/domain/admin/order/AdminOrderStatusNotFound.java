package com.github.shop.domain.admin.order;

public class AdminOrderStatusNotFound extends Exception {
    public AdminOrderStatusNotFound() {
        super("Undefined order status");
    }
}
