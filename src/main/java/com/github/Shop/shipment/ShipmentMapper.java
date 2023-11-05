package com.github.Shop.shipment;

import com.github.Shop.cart.Cart;
import com.github.Shop.orderrow.OrderRow;

import static com.github.Shop.order.OrderManager.getQuantity;

public class ShipmentMapper {
    public static OrderRow mapToShipmentRow(Cart cart, Long orderId, Shipment shipment) {
        return OrderRow.builder()
                .quantity(getQuantity(cart))
                .price(shipment.getPrice())
                .shipmentId(shipment.getId())
                .orderId(orderId)
                .build();
    }
}
