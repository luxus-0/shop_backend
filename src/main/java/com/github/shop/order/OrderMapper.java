package com.github.shop.order;

import com.github.shop.cart.Cart;
import com.github.shop.cartitem.CartItem;
import com.github.shop.order.dto.OrderDto;
import com.github.shop.order.dto.OrderSummary;
import com.github.shop.payment.Payment;
import com.github.shop.shipment.Shipment;

import java.math.BigDecimal;
import java.util.List;

import static com.github.shop.customer.CustomerService.createCustomers;

public class OrderMapper {
    public static Order createOrder(OrderDto orderDto, Cart cart, Shipment shipment, Payment payment) {
        return Order.builder()
                .customers(createCustomers(orderDto))
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculateGrossValue(cart.getItems(), shipment))
                .placeDate(orderDto.placeDate())
                .payment(payment)
                .build();
    }

    private static BigDecimal calculateGrossValue(List<CartItem> items, Shipment shipment) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipment.getPrice());
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