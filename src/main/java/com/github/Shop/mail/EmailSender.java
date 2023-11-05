package com.github.Shop.mail;

import jakarta.mail.MessagingException;

public interface EmailSender {
    void send(String to, String subject, String body) throws MessagingException;
}
