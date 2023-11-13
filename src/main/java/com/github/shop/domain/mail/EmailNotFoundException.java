package com.github.shop.domain.mail;

public class EmailNotFoundException extends Exception {
    public EmailNotFoundException() {
        super("Email not found");
    }
}
