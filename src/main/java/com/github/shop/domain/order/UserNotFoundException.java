package com.github.shop.domain.order;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(){
        super("User not found");
    }
}
