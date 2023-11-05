package com.github.Shop.mail;

import com.github.Shop.order.Order;
import jakarta.mail.MessagingException;

public interface EmailSender {
    void send(String to, String subject, String body) throws MessagingException;
}
