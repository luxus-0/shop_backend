package com.github.shop.infrastructure.security.register;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String username){
        super("User with username " + username + " already exists.");
    }
}
