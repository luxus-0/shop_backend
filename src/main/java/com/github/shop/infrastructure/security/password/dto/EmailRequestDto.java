package com.github.shop.infrastructure.security.password.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailRequestDto(@NotBlank String email) {
}
