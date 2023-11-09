package com.github.shop.adminorder;

public class UndefinedOrderStatus extends Exception {
    public UndefinedOrderStatus() {
        super("Undefined order status");
    }
}
