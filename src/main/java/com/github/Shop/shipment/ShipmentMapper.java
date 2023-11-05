package com.github.Shop.shipment;

import com.github.Shop.cart.Cart;
import com.github.Shop.cartitem.CartItem;
import com.github.Shop.orderrow.OrderRow;

public class ShipmentMapper {
    public static OrderRow mapToShipmentRow(Cart cart, Long orderId, Shipment shipment) {
        return OrderRow.builder()
                .quantity(getQuantity(cart))
                .price(shipment.getPrice())
                .shipmentId(shipment.getId())
                .orderId(orderId)
                .build();
    }

    private static Integer getQuantity(Cart cart) {
        return cart.getItems().stream()
                .map(CartItem::getQuantity)
                .findAny()
                .orElseThrow();
    }
}
