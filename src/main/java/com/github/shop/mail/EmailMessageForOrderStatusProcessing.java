package com.github.shop.mail;

import com.github.shop.order.Order;
import com.github.shop.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailMessageForOrderStatusProcessing implements EmailMessage {
    @Override
    public String getSubject(Order order, OrderStatus newOderStatus) {
        return "Order" + order.getId() + "successfully processed ";
    }

    @Override
    public String getBody(Order order, OrderStatus newOrderStatus) {
        {
            return String.format("""
                    Your order ID: %s
                     \nStatus changed for %s
                     \nYour order is processing by our employee
                     \nAfter completing the order, we will proceed with the shipment promptly.
                     \nBest regards,
                     \nShop Łukasz""", order.getId(), newOrderStatus);
        }
    }
}
