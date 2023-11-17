package com.github.shop.infrastructure.security.token.dto;

import lombok.Builder;

@Builder
public record JwtResponseDto(String username, String token, boolean checkAdmin) {
}
