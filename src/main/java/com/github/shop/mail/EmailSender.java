package com.github.shop.mail;

import com.github.shop.mail.dto.EmailDto;
import jakarta.mail.MessagingException;

public interface EmailSender {
    void send(EmailDto email) throws MessagingException;
}
