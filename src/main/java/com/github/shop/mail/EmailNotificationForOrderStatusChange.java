package com.github.shop.mail;

import com.github.shop.admin.order.AdminOrderStatusNotFound;
import com.github.shop.mail.dto.EmailDto;
import com.github.shop.order.Order;
import com.github.shop.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailNotificationForOrderStatusChange {

    private final EmailService emailService;

    public EmailMessage readEmailNotificationForOrderStatus(OrderStatus newOrderStatus) throws AdminOrderStatusNotFound {
        switch (newOrderStatus) {
            case PROCESSING -> {
                return new EmailMessageForOrderStatusProcessing();
            }
            case COMPLETED -> {
                return new EmailMessageForOrderStatusSuccess();
            }
            case REFUND -> {
                return new EmailMessageForOrderStatusRefund();
            }
            case null, default -> throw new AdminOrderStatusNotFound();
        }
    }

    public void sendEmailNotification(OrderStatus newOrderStatus, Order order) throws AdminOrderStatusNotFound {
       EmailMessage emailMessage = readEmailNotificationForOrderStatus(newOrderStatus);
        EmailDto emailDto = EmailDto.builder()
                .email(emailService.getEmail(order))
                .body(emailMessage.getBody(order, newOrderStatus))
                .subject(emailMessage.getSubject(order, newOrderStatus))
                .build();
        emailService.send(emailDto);
    }
}
