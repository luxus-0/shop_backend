package com.github.shop.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.github.shop.constant.Constants.PHONE_REGEX;

public record ContactDto(@NotNull @Pattern(regexp = PHONE_REGEX) String phone, @NotNull @Email String email) {
}
