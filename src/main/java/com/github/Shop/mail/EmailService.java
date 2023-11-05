package com.github.Shop.mail;

import com.github.Shop.currency.Currency;
import com.github.Shop.order.Order;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Log4j2
@Service
public class EmailService implements EmailSender {
    private final JavaMailSender javaMailSender;

    @Async
    @Override
    public void send(String to, String subject, String htmlBody) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessage = new MimeMessageHelper(message);
            mimeMessage.setTo(toEmail(to));
            mimeMessage.setReplyTo(toEmail(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(htmlBody, true);

            javaMailSender.send(message);
            log.info("Email send successfully");
        } catch (MessagingException e) {
            log.error("Error sending email: " + e.fillInStackTrace());
        }
    }

    private static String toEmail(String to) {
        return to.replaceAll("'", " ");
    }

    public static String createEmailMessage(Order order) {
        return "<html><body><h2>Order Details</h2>"
                + "<ul>"
                + "<li><strong>Order ID:</strong> " + order.getId() + "</li>"
                + "<li><strong>Date Ordered:</strong> " + order.getPlaceDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "</li>"
                + "<li><strong>Order Status:</strong> " + order.getOrderStatus() + "</li>"
                + "<li><strong>Price:</strong> " + order.getGrossValue() + " " + Currency.PLN + "</li>"
                + "<li><strong>Payment Method:</strong> " + order.getPayment().getName() + "</li>"
                + "<li><strong>Description:</strong> " + order.getPayment().getDescription() + "</li>"
                + "</ul>"
                + "</body>"
                + "</html>";
    }

    public static String createEmailSubject(Order order) {
        return "Order ID: " + order.getId();
    }
}
