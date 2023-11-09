package com.github.shop.mail;

public class EmailNotFoundException extends Exception {
    public EmailNotFoundException() {
        super("Email not found");
    }
}
