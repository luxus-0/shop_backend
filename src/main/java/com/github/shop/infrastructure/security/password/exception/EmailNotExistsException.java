package com.github.shop.infrastructure.security.password.exception;

public class EmailNotExistsException extends Exception{
    public EmailNotExistsException(){
        super("Email not exists");
    }
}
