package com.github.shop.domain.payment.dto;

import com.github.shop.domain.payment.PaymentType;
import jakarta.validation.constraints.NotBlank;

public record PaymentDto(@NotBlank String name, @NotBlank PaymentType type, @NotBlank String description,
                         @NotBlank boolean isPayment) {
}
