package com.github.shop.mail;

import com.github.shop.adminorder.AdminOrder;
import com.github.shop.contact.Contact;
import com.github.shop.currency.Currency;
import com.github.shop.mail.dto.EmailDto;
import com.github.shop.order.Order;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.github.shop.customer.CustomerService.getCustomerEmail;

@RequiredArgsConstructor
@Log4j2
@Service
public class EmailService implements EmailSender {
    private final JavaMailSender javaMailSender;

    @Async
    @Override
    public void send(EmailDto email) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessage = new MimeMessageHelper(message);
            mimeMessage.setTo(email.email());
            mimeMessage.setSubject(email.subject());
            mimeMessage.setText(email.body());

            javaMailSender.send(message);
            log.info("Email send successfully");
        } catch (MessagingException e) {
            log.error("Error sending email: " + e.fillInStackTrace());
        }
    }

    public void sendEmail(Order order) {
        send(new EmailDto(getEmail(order), getSubject(order), getBody(order)));
    }

    public String getBody(Order order) {
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

    public String getSubject(Order order) {
        return "Order ID: " + order.getId();
    }

    private String getEmail(Order order) {
        try {
            return getCustomerEmail(order);
        } catch (EmailNotFoundException e) {
            log.error(e.getMessage());
        }
        return "";
    }
}
