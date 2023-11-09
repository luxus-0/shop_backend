package com.github.shop.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * SEND EMAIL TO CLIENT WITH BODY HTML
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emails")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public void sendEmail(@RequestParam("email") String to, @RequestParam("subject") String subject, @RequestParam("body") String body) {
        emailService.send(to, subject, body);
    }
}
