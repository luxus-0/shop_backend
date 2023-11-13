package com.github.shop.domain.payment;

public class PaymentNotFoundException extends Exception {
    public PaymentNotFoundException() {
        super("Payment not found");
    }
}
