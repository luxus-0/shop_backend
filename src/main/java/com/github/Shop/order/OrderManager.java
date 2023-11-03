package com.github.Shop.order;

import com.github.Shop.address.Address;
import com.github.Shop.cart.Cart;
import com.github.Shop.cart.CartRepository;
import com.github.Shop.cartitem.CartItem;
import com.github.Shop.cartitem.CartItemRepository;
import com.github.Shop.contact.Contact;
import com.github.Shop.customer.Customer;
import com.github.Shop.customer.dto.CustomerDto;
import com.github.Shop.order.dto.OrderDto;
import com.github.Shop.order.dto.OrderSummary;
import com.github.Shop.orderrow.OrderRow;
import com.github.Shop.orderrow.OrderRowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
class OrderManager {

    private final OrderRepository orderRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public OrderSummary getOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.cartId()).orElseThrow();
        Order order = Order.builder()
                .customers(createCustomers(orderDto))
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculateGrossValue(cart.getItems()))
                .placeDate(orderDto.placeDate())
                .build();

        Order newOrder = orderRepository.save(order);
        createOrderRows(cart, newOrder.getId());
        cartItemRepository.deleteAllByCartId(orderDto.cartId());
        cartRepository.deleteCartById(orderDto.cartId());

        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossValue(newOrder.getGrossValue())
                .build();
    }

    private BigDecimal calculateGrossValue(List<CartItem> items) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private void createOrderRows(Cart cart, Long id) {
        cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .orderId(id)
                        .productId(cartItem.getProduct().getId())
                        .price(cartItem.getProduct().getPrice())
                        .quantity(cartItem.getQuantity())
                        .build())
                .peek(orderRowRepository::save)
                .forEach(log::info);
    }

    private List<Customer> createCustomers(OrderDto orderDto) {
        return List.of(Customer.builder()
                .firstName(orderDto.customer().firstName())
                .lastName(orderDto.customer().lastName())
                .pesel(orderDto.customer().pesel())
                .addresses(List.of(Address.builder()
                        .street(orderDto.address().street())
                        .city(orderDto.address().city())
                        .zipCode(orderDto.address().zipCode())
                        .build()))
                .contacts(List.of(Contact.builder()
                        .email(orderDto.contact().email())
                        .phone(orderDto.contact().phone())
                        .build()))
                .build());
    }
}
