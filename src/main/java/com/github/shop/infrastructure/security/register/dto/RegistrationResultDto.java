package com.github.shop.infrastructure.security.register.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegistrationResultDto(Long id, boolean created,
                                    @Email @NotBlank(message = "${not.blank.username}")
                                    String username,
                                    String token) {
}
