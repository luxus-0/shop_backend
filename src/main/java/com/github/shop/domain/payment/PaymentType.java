package com.github.shop.domain.payment;

import lombok.Getter;

@Getter
public enum PaymentType {
    CREDIT_CARD,
    PAYPAL,
    GOOGLE_PAY,
    APPLE_PAY,
    BANK_TRANSFER
}
