package com.github.shop.mail.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record EmailDto(@Email String email, @NotNull String subject,@NotNull String body) {
}
