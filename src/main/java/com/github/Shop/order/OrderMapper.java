package com.github.Shop.order;

import com.github.Shop.cart.Cart;
import com.github.Shop.cartitem.CartItem;
import com.github.Shop.order.dto.OrderDto;
import com.github.Shop.payment.Payment;
import com.github.Shop.shipment.Shipment;

import java.math.BigDecimal;
import java.util.List;

import static com.github.Shop.customer.CustomerService.createCustomers;

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
}
