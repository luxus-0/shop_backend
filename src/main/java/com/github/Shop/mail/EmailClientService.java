package com.github.Shop.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailClientService {

    @Value("${email.sender}")
    private String emailProperties;
    private final Map<String, EmailSender> emailSenderMap;

    public EmailSender getInstance(){
        if(emailProperties.equals("emailService")) {
            return emailSenderMap.get("emailService");
        }
        return emailSenderMap.get("fakeEmailService");
    }
}
