package com.github.Shop.mail;

public class EmailNotFoundException extends Exception{
    public EmailNotFoundException(){
        super("Email not found");
    }
}
