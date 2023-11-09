package com.github.shop.mail;

import com.github.shop.adminorder.AdminOrder;
import com.github.shop.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailPayloadForOrderStatusProcessing implements EmailPayload {
    @Override
    public String getSubject(AdminOrder adminOrder, OrderStatus newOderStatus) {
        return "Order" + adminOrder.getId() + "successfully processed ";
    }

    @Override
    public String getBody(AdminOrder adminOrder, OrderStatus newOrderStatus) {
        {
            return String.format("""
                    Your order %s is processing
                     \nStatus changed for %s
                     \nYour order is processing by our employee
                     \nAfter completing the order, we will proceed with the shipment promptly.
                     \nBest regards,
                     \nShop Łukasz""", adminOrder.getId(), newOrderStatus);
        }
    }
}
