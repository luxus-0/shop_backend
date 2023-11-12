package com.github.shop.security.login.dto;

import lombok.Builder;

public record UserDto(Long id, String username, String password) {
}
