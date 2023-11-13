package com.github.shop.domain.mail;

import com.github.shop.domain.order.Order;
import com.github.shop.domain.order.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class EmailMessageForOrderStatusSuccess implements EmailMessage {
    @Override
    public String getSubject(Order order, OrderStatus orderStatus) {
        return "Order" + order.getId() + "successfully ordered";
    }

    @Override
    public String getBody(Order order, OrderStatus newOrderStatus) {
        return String.format("""
                Your order ID: %s +
                \nOrder status: %s +
                \nOrder date: %s +
                \nGross value: %s +
                \nPayment type: %s +
                 \nBest regards,
                 \nShop Łukasz""",
                order.getId(), newOrderStatus, order.getPlaceDate(), order.getGrossValue(),
                order.getPayment().getName());
    }
}
