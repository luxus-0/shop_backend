package com.github.shop.infrastructure.security.login;

import lombok.Getter;

@Getter
public enum UserRole {
    ROLE_ADMIN("Admin"),
    ROLE_CUSTOMER("Customer");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }


}
