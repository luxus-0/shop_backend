package com.github.shop.payment;

import lombok.Getter;

@Getter
public enum PaymentType {
    CREDIT_CARD,
    PAYPAL,
    GOOGLE_PAY,
    APPLE_PAY,
    BANK_TRANSFER
}
