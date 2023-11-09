package com.github.shop.mail;

import com.github.shop.adminorder.AdminOrder;
import com.github.shop.order.OrderStatus;

public interface EmailPayload {
    String getSubject(AdminOrder adminOrder, OrderStatus newOderStatus);

    String getBody(AdminOrder adminOrder, OrderStatus newOrderStatus);
}
