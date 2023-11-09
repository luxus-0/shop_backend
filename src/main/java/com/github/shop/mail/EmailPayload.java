package com.github.shop.mail;

import com.github.shop.adminorder.AdminOrder;
import com.github.shop.order.OrderStatus;

public interface EmailPayload {
    String getSubjectEmail(AdminOrder adminOrder, OrderStatus newOderStatus);

    String getBodyEmail(AdminOrder adminOrder, OrderStatus newOrderStatus);
}
