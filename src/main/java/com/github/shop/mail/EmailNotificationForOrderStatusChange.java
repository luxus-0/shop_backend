package com.github.shop.mail;

import com.github.shop.adminorder.AdminOrder;
import com.github.shop.adminorder.UndefinedOrderStatus;
import com.github.shop.mail.dto.EmailDto;
import com.github.shop.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailNotificationForOrderStatusChange {

    private final EmailService emailService;

    public EmailPayload readEmailForOrderStatus(OrderStatus newOrderStatus) throws UndefinedOrderStatus {
        switch (newOrderStatus) {
            case PROCESSING -> {
                return new EmailPayloadForOrderStatusProcessing();
            }
            case COMPLETED -> {
                return new EmailPayloadForOrderStatusSuccess();
            }
            case REFUND -> {
                return new EmailPayloadForOrderStatusRefund();
            }
            case null, default -> throw new UndefinedOrderStatus();
        }
    }

    public void sendEmailNotification(OrderStatus newOrderStatus, AdminOrder adminOrder) throws UndefinedOrderStatus {
        EmailPayload email = readEmailForOrderStatus(newOrderStatus);
        EmailDto emailDto = new EmailDto(emailService.getEmail(adminOrder), email.getSubject(adminOrder, newOrderStatus), email.getBody(adminOrder, newOrderStatus));
        emailService.send(emailDto);
    }
}
