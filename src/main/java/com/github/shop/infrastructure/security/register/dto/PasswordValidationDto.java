package com.github.shop.infrastructure.security.register.dto;

public record PasswordValidationDto(String passwordMessage, boolean isEqualsPassword) {
}
