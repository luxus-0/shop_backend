package com.github.shop.domain.mail.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record EmailDto(@Email String email, @NotNull String subject, @NotNull String body) {
}
