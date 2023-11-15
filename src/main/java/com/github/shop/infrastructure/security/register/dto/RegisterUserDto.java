package com.github.shop.infrastructure.security.register.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.github.shop.infrastructure.security.constant.Constant.REGEX_PASSWORD;

public record RegisterUserDto(@Email
                              @NotBlank(message = "${not.blank.username}")
                              String username,
                              @Pattern (regexp = REGEX_PASSWORD)
                              @NotBlank(message = "${not.blank.password}")
                              String password,
                              @Pattern (regexp = REGEX_PASSWORD)
                              String repeatPassword)

{
}
