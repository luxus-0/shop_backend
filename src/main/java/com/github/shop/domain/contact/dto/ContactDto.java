package com.github.shop.domain.contact.dto;

import com.github.shop.domain.constant.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ContactDto(@NotNull @Pattern(regexp = Constants.PHONE_REGEX) String phone, @NotNull @Email String email) {
}
