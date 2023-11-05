package com.github.Shop.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailClientService {

    private final Map<String, EmailSender> emailSenderMap;
    @Value("${email.sender}")
    private String emailProperties;

    public EmailSender getInstance() {
        if (emailProperties.equals("emailService")) {
            return emailSenderMap.get("emailService");
        }
        return emailSenderMap.get("fakeEmailService");
    }
}
