package com.github.shop.domain.order;

import com.github.shop.domain.cart.Cart;
import com.github.shop.domain.cartitem.CartItem;
import com.github.shop.domain.order.dto.OrderDto;
import com.github.shop.domain.order.dto.OrderListDto;
import com.github.shop.domain.order.dto.OrderSummary;
import com.github.shop.domain.payment.Payment;
import com.github.shop.domain.shipment.Shipment;

import java.math.BigDecimal;
import java.util.List;

import static com.github.shop.domain.customer.CustomerService.createCustomers;

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
