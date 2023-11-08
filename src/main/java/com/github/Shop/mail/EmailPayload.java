package com.github.Shop.mail;

import com.github.Shop.adminorder.AdminOrder;
import com.github.Shop.order.OrderStatus;

public interface EmailPayload {
    String getSubjectEmail(AdminOrder adminOrder, OrderStatus newOderStatus);

    String getBodyEmail(AdminOrder adminOrder, OrderStatus newOrderStatus);
}
