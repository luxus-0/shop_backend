package com.github.shop.orderrow;

import com.github.shop.cartitem.CartItem;

public class OderRowMapper {
    public static OrderRow mapToOrderRow(Long orderId, CartItem cartItem) {
        return OrderRow.builder()
                .orderId(orderId)
                .productId(cartItem.getProduct().getId())
                .price(cartItem.getProduct().getPrice())
                .quantity(cartItem.getQuantity())
                .build();
    }
}
