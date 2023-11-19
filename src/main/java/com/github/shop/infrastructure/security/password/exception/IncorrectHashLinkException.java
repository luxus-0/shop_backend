package com.github.shop.infrastructure.security.password.exception;

public class IncorrectHashLinkException extends Exception{
    public IncorrectHashLinkException(){
        super("Incorrect link");
    }
}
