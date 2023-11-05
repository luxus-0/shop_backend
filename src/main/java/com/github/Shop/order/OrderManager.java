package com.github.Shop.order;

import com.github.Shop.cart.Cart;
import com.github.Shop.cart.CartNotFoundException;
import com.github.Shop.cart.CartRepository;
import com.github.Shop.cartitem.CartItem;
import com.github.Shop.cartitem.CartItemRepository;
import com.github.Shop.mail.EmailService;
import com.github.Shop.order.dto.OrderDto;
import com.github.Shop.order.dto.OrderSummary;
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

import static com.github.Shop.order.OrderMapper.createOrder;
import static com.github.Shop.order.OrderMapper.createOrderSummary;
import static com.github.Shop.orderrow.OderRowMapper.mapToOrderRow;
import static com.github.Shop.shipment.ShipmentMapper.mapToShipmentRow;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderManager {

    private final OrderRepository orderRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailService emailService;

    @Transactional
    public OrderSummary getOrder(OrderDto orderDto) throws ShipmentNotFoundException, PaymentNotFoundException, MessagingException, CartNotFoundException {
        Cart cart = findCart(orderDto);
        Shipment shipment = findShipment(orderDto);
        Payment payment = findPayment(orderDto);
        Order savedOrder = orderRepository.save(createOrder(orderDto, cart, shipment, payment));
        saveOrderRows(cart, savedOrder.getId(), shipment);
        deleteCartAndCartItem(orderDto);
        emailService.sendEmail(savedOrder);
        return createOrderSummary(savedOrder);
    }

    private Payment findPayment(OrderDto orderDto) throws PaymentNotFoundException {
        return paymentRepository.findById(orderDto.paymentId())
                .orElseThrow(PaymentNotFoundException::new);
    }

    private Shipment findShipment(OrderDto orderDto) throws ShipmentNotFoundException {
        return shipmentRepository.findById(orderDto.shipmentId())
                .orElseThrow(ShipmentNotFoundException::new);
    }

    private Cart findCart(OrderDto orderDto) throws CartNotFoundException {
        return cartRepository.findById(orderDto.cartId())
                .orElseThrow(CartNotFoundException::new);
    }

    private void deleteCartAndCartItem(OrderDto orderDto) {
        cartItemRepository.deleteAllByCartId(orderDto.cartId());
        cartRepository.deleteCartById(orderDto.cartId());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(cart, orderId, shipment);
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> mapToOrderRow(orderId, cartItem))
                .peek(orderRowRepository::save)
                .forEach(log::info);
    }

    private void saveShipmentRow(Cart cart, Long orderId, Shipment shipment) {
        orderRowRepository.save(mapToShipmentRow(cart, orderId, shipment));
    }
}
