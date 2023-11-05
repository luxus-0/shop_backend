package com.github.Shop.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;
    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void sendHtmlEmailTest() throws MessagingException, IOException {
        //given
            String to = "luxus0@gmail.com";
            String subject = "Testowy temat";
            String htmlBody = "<html><body><h1>Testowy tytuł</h1><p>To jest treść wiadomości HTML.</p></body></html>";

            //when
        emailService.send(to, subject, htmlBody);
       ArgumentCaptor<MimeMessage> argument = ArgumentCaptor.forClass(MimeMessage.class);
       verify(javaMailSender, times(1)).send(argument.capture());
         assertEquals(to, argument.getValue().getAllRecipients()[0].toString());
        assertEquals(subject, argument.getValue().getSubject());
        assertEquals(htmlBody, argument.getValue().getContent());
    }


}