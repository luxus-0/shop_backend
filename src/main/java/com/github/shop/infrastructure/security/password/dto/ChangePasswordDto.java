package com.github.shop.infrastructure.security.password.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.github.shop.infrastructure.security.constant.Constant.REGEX_PASSWORD;

public record ChangePasswordDto(
        @NotBlank @Pattern(regexp = REGEX_PASSWORD) String password,
        @NotBlank @Pattern(regexp = REGEX_PASSWORD) String repeatPassword,
        @NotBlank String hash) {
}
