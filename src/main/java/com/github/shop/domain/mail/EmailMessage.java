package com.github.shop.domain.mail;

import com.github.shop.domain.order.Order;
import com.github.shop.domain.order.OrderStatus;

public interface EmailMessage {
    String getSubject(Order order, OrderStatus newOderStatus);

    String getBody(Order order, OrderStatus newOrderStatus);
}
