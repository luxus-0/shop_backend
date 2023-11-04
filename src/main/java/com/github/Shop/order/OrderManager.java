package com.github.Shop.order;

import com.github.Shop.address.Address;
import com.github.Shop.cart.Cart;
import com.github.Shop.cart.CartRepository;
import com.github.Shop.cartitem.CartItem;
import com.github.Shop.cartitem.CartItemRepository;
import com.github.Shop.contact.Contact;
import com.github.Shop.customer.Customer;
import com.github.Shop.order.dto.OrderDto;
import com.github.Shop.order.dto.OrderSummary;
import com.github.Shop.orderrow.OrderRow;
import com.github.Shop.orderrow.OrderRowRepository;
import com.github.Shop.shipment.Shipment;
import com.github.Shop.shipment.ShipmentNotFoundException;
import com.github.Shop.shipment.ShipmentRepository;
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
    private final ShipmentRepository shipmentRepository;

    @Transactional
    public OrderSummary getOrder(OrderDto orderDto) throws ShipmentNotFoundException {
        Cart cart = cartRepository.findById(orderDto.cartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.shipmentId()).orElseThrow(ShipmentNotFoundException::new);

        Order order = createOrder(orderDto, cart, shipment);
        Order newOrder = orderRepository.save(order);

        saveOrderRows(cart, newOrder.getId(), shipment);

        deleteCartAndCartItem(orderDto);

        return getOrderSummary(newOrder);
    }

    private void deleteCartAndCartItem(OrderDto orderDto) {
        cartItemRepository.deleteAllByCartId(orderDto.cartId());
        cartRepository.deleteCartById(orderDto.cartId());
    }

    private static OrderSummary getOrderSummary(Order newOrder) {
        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossValue(newOrder.getGrossValue())
                .build();
    }

    private Order createOrder(OrderDto orderDto, Cart cart, Shipment shipment) {
        return Order.builder()
                .customers(createCustomers(orderDto))
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculateGrossValue(cart.getItems(), shipment))
                .placeDate(orderDto.placeDate())
                .build();
    }

    private BigDecimal calculateGrossValue(List<CartItem> items, Shipment shipment) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipment.getPrice());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(cart, orderId, shipment);
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .orderId(orderId)
                        .productId(cartItem.getProduct().getId())
                        .price(cartItem.getProduct().getPrice())
                        .quantity(cartItem.getQuantity())
                        .build())
                .peek(orderRowRepository::save)
                .forEach(log::info);
    }

    private void saveShipmentRow(Cart cart, Long orderId, Shipment shipment) {
        orderRowRepository.save(OrderRow.builder()
                        .quantity(getQuantity(cart))
                        .price(shipment.getPrice())
                        .shipmentId(shipment.getId())
                        .orderId(orderId)
                .build());
    }

    private static Integer getQuantity(Cart cart) {
        return cart.getItems().stream().map(CartItem::getQuantity).findAny().orElseThrow();
    }

    private List<Customer> createCustomers(OrderDto orderDto) {
        return List.of(Customer.builder()
                .firstName(orderDto.customer().firstName())
                .lastName(orderDto.customer().lastName())
                .pesel(orderDto.customer().pesel())
                .addresses(getAddresses(orderDto))
                .contacts(getContacts(orderDto))
                .build());
    }

    private static List<Contact> getContacts(OrderDto orderDto) {
        return List.of(Contact.builder()
                .email(orderDto.contact().email())
                .phone(orderDto.contact().phone())
                .build());
    }

    private static List<Address> getAddresses(OrderDto orderDto) {
        return List.of(Address.builder()
                .street(orderDto.address().street())
                .city(orderDto.address().city())
                .zipCode(orderDto.address().zipCode())
                .build());
    }
}
