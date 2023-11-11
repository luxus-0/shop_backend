package com.github.shop.mail;

import com.github.shop.order.Order;
import com.github.shop.order.OrderStatus;

public interface EmailMessage {
    String getSubject(Order order, OrderStatus newOderStatus);
    String getBody(Order order, OrderStatus newOrderStatus);
}
