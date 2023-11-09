package com.github.shop.mail;

import com.github.shop.adminorder.AdminOrder;
import com.github.shop.order.OrderStatus;

public class EmailPayloadForOrderStatusRefund implements EmailPayload {
    @Override
    public String getSubjectEmail(AdminOrder adminOrder, OrderStatus newOderStatus) {
        return "Order" + adminOrder.getId() + "refund money to account";
    }

    @Override
    public String getBodyEmail(AdminOrder adminOrder, OrderStatus newOrderStatus) {
        {
            return String.format("""
                    Your order %s
                     \nStatus changed for %s
                     \nYour money is refund on your account
                     \nBest regards,
                     \nShop Łukasz""", adminOrder.getId(), newOrderStatus);
        }
    }
}
