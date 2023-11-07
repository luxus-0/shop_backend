package com.github.Shop.mail;

import com.github.Shop.adminorder.AdminOrder;
import com.github.Shop.order.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class EmailPayloadForOrderStatusSuccess implements EmailPayload {
    @Override
    public String getSubjectEmail(AdminOrder adminOrder, OrderStatus orderStatus) {
        return "Order" + adminOrder.getId() + "successfully ordered";
    }

    @Override
    public String getBodyEmail(AdminOrder adminOrder, OrderStatus newOrderStatus) {
        return String.format("""
                Your order id: %s +
                status %s
                 \nBest regards,
                 \nShop Łukasz""", adminOrder.getId(), newOrderStatus);
    }

}
