package config;

import com.github.shop.domain.mail.EmailSender;
import com.github.shop.domain.mail.EmailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailConfig {
    @Bean
    @ConditionalOnProperty(name = "email.sender", havingValue = "emailService")
    public EmailSender emailService(JavaMailSender javaMailSender) {
        return new EmailService(javaMailSender);

    }
}
