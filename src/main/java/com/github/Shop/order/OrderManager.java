package com.github.Shop.order;

import com.github.Shop.cart.Cart;
import com.github.Shop.cart.CartNotFoundException;
import com.github.Shop.cart.CartRepository;
import com.github.Shop.cartitem.CartItem;
import com.github.Shop.cartitem.CartItemRepository;
import com.github.Shop.contact.Contact;
import com.github.Shop.customer.Customer;
import com.github.Shop.mail.EmailClientService;
import com.github.Shop.mail.EmailService;
import com.github.Shop.mail.EmailNotFoundException;
import com.github.Shop.order.dto.OrderDto;
import com.github.Shop.order.dto.OrderSummary;
import com.github.Shop.orderrow.OrderRow;
import com.github.Shop.orderrow.OrderRowRepository;
import com.github.Shop.payment.Payment;
import com.github.Shop.payment.PaymentNotFoundException;
import com.github.Shop.payment.PaymentRepository;
import com.github.Shop.shipment.Shipment;
import com.github.Shop.shipment.ShipmentNotFoundException;
import com.github.Shop.shipment.ShipmentRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.github.Shop.address.AddressService.getAddresses;
import static com.github.Shop.contact.ContactService.getContacts;
import static com.github.Shop.mail.EmailService.createEmailMessage;
import static com.github.Shop.mail.EmailService.createEmailSubject;

@Service
@RequiredArgsConstructor
@Log4j2
class OrderManager {

    private final OrderRepository orderRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailService emailService;
    private final EmailClientService emailClientService;

    @Transactional
    public OrderSummary getOrder(OrderDto orderDto) throws ShipmentNotFoundException, PaymentNotFoundException, MessagingException, CartNotFoundException {
        Cart cart = findCart(orderDto);
        Shipment shipment = findShipment(orderDto);
        Payment payment = findPayment(orderDto);

        Order order = createOrder(orderDto, cart, shipment, payment);
        Order savedOrder = orderRepository.save(order);

        saveOrderRows(cart, savedOrder.getId(), shipment);

        deleteCartAndCartItem(orderDto);

        sendEmail(order);
        return getOrderSummary(savedOrder);
    }

    private Payment findPayment(OrderDto orderDto) throws PaymentNotFoundException {
        return paymentRepository.findById(orderDto.paymentId()).orElseThrow(PaymentNotFoundException::new);
    }

    private Shipment findShipment(OrderDto orderDto) throws ShipmentNotFoundException {
        return shipmentRepository.findById(orderDto.shipmentId()).orElseThrow(ShipmentNotFoundException::new);
    }

    private Cart findCart(OrderDto orderDto) throws CartNotFoundException {
        return cartRepository.findById(orderDto.cartId()).orElseThrow(CartNotFoundException::new);
    }

    private void sendEmail(Order order) throws MessagingException {
        emailClientService.getInstance().send(getEmail(order), createEmailSubject(order), createEmailMessage(order));
    }

    private String getEmail(Order order) {
        try {
            return order.getCustomers().stream()
                    .flatMap(customer -> customer.getContacts().stream())
                    .map(Contact::getEmail)
                    .findAny()
                    .orElseThrow(EmailNotFoundException::new);
        } catch (EmailNotFoundException e) {
            log.error(e.getMessage());
        }
        return "";
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
                .payment(newOrder.getPayment())
                .build();
    }

    private Order createOrder(OrderDto orderDto, Cart cart, Shipment shipment, Payment payment) {
        return Order.builder()
                .customers(createCustomers(orderDto))
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculateGrossValue(cart.getItems(), shipment))
                .placeDate(orderDto.placeDate())
                .payment(payment)
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
        return cart.getItems().stream()
                .map(CartItem::getQuantity)
                .findAny()
                .orElseThrow();
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
}
