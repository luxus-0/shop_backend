package com.github.Shop.payment.dto;

import com.github.Shop.payment.PaymentType;
import jakarta.validation.constraints.NotBlank;

public record PaymentDto(@NotBlank String name, @NotBlank PaymentType type,@NotBlank String description, @NotBlank boolean isPayment) {
}
