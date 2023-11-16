package com.github.shop.domain.mail;

import com.github.shop.domain.order.Order;
import com.github.shop.domain.order.OrderStatus;

public class EmailMessageForOrderStatusRefund implements EmailMessage {
    @Override
    public String getSubject(Order order, OrderStatus newOderStatus) {
        return "Order ID: " + order.getId() + " refund money to account";
    }

    @Override
    public String getBody(Order order, OrderStatus newOrderStatus) {
        {
            return String.format("""
                    Your order %s
                     \nStatus changed for %s
                     \nYour money is refund on your account
                     \nBest regards,
                     \nShop Łukasz""", order.getId(), newOrderStatus);
        }
    }
}
