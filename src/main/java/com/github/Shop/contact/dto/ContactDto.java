package com.github.Shop.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactDto(@NotBlank String phone, @NotBlank @Email String email) {
}
