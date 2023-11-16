package com.github.shop.domain.order;

import com.github.shop.domain.order.dto.OrderListDto;
import com.github.shop.domain.order.dto.OrderSummary;

import java.util.List;

public class OrderMapper {

    public static List<OrderListDto> mapToOrderListDto(List<Order> orders) {
        return orders.stream()
                .map(order -> OrderListDto.builder()
                        .id(order.getId())
                        .orderStatus(order.getOrderStatus())
                        .placeDate(order.getPlaceDate())
                        .grossValue(order.getGrossValue())
                        .build())
                .toList();
    }

    public static OrderSummary createOrderSummary(Order newOrder) {
        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossValue(newOrder.getGrossValue())
                .payment(newOrder.getPayment())
                .build();
    }
}
