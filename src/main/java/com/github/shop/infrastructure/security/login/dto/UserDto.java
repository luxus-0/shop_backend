package com.github.shop.infrastructure.security.login.dto;

import lombok.Builder;

public record UserDto(Long id, String username, String password) {
}
