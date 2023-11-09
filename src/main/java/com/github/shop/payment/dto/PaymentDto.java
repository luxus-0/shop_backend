package com.github.shop.payment.dto;

import com.github.shop.payment.PaymentType;
import jakarta.validation.constraints.NotBlank;

public record PaymentDto(@NotBlank String name, @NotBlank PaymentType type, @NotBlank String description,
                         @NotBlank boolean isPayment) {
}
