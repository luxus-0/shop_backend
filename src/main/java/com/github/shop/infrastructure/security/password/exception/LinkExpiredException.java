package com.github.shop.infrastructure.security.password.exception;

public class LinkExpiredException extends Exception {
    public LinkExpiredException(){
        super("Link has expired");
    }
}
