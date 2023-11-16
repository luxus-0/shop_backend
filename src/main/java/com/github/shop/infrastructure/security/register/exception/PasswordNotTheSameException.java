package com.github.shop.infrastructure.security.register.exception;

public class PasswordNotTheSameException extends Exception {
    public PasswordNotTheSameException() {
        super("Passwords not the same");
    }
}
