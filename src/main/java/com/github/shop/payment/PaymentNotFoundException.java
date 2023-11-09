package com.github.shop.payment;

public class PaymentNotFoundException extends Exception {
    public PaymentNotFoundException() {
        super("Payment not found");
    }
}
