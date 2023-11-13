package com.github.shop.domain.shipment;

import com.github.shop.domain.cart.Cart;
import com.github.shop.domain.cartitem.CartItem;
import com.github.shop.domain.orderrow.OrderRow;

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
