package com.github.Shop.payment;

public class PaymentNotFoundException extends Exception{
    public PaymentNotFoundException(){
        super("Payment not found");
    }
}
