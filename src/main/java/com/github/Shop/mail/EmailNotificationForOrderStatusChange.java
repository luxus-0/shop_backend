package com.github.Shop.mail;

import com.github.Shop.adminorder.AdminOrder;
import com.github.Shop.adminorder.UndefinedOrderStatus;
import com.github.Shop.contact.Contact;
import com.github.Shop.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailNotificationForOrderStatusChange {

    private final EmailService emailService;

    public EmailPayload readEmailPayloadForOrderStatus(OrderStatus newOrderStatus) throws UndefinedOrderStatus {
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
        EmailPayload emailPayload = readEmailPayloadForOrderStatus(newOrderStatus);
        emailService.send(emailService.getEmail(adminOrder), emailPayload.getBodyEmail(adminOrder, newOrderStatus), emailPayload.getSubjectEmail(adminOrder, newOrderStatus));
    }
}