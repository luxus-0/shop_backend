package com.github.shop.domain.mail;

import com.github.shop.domain.mail.dto.EmailDto;
import jakarta.mail.MessagingException;

public interface EmailSender {
    void send(EmailDto email) throws MessagingException;
}
