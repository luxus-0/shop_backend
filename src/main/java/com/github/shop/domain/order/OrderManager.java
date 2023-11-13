package com.github.shop.domain.order;

import com.github.shop.domain.cart.Cart;
import com.github.shop.domain.cart.CartNotFoundException;
import com.github.shop.domain.cart.CartRepository;
import com.github.shop.domain.cartitem.CartItemRepository;
import com.github.shop.domain.mail.EmailService;
import com.github.shop.domain.order.dto.OrderDto;
import com.github.shop.domain.order.dto.OrderSummary;
import com.github.shop.domain.orderrow.OderRowMapper;
import com.github.shop.domain.orderrow.OrderRowRepository;
import com.github.shop.domain.payment.Payment;
import com.github.shop.domain.payment.PaymentNotFoundException;
import com.github.shop.domain.payment.PaymentRepository;
import com.github.shop.domain.shipment.Shipment;
import com.github.shop.domain.shipment.ShipmentMapper;
import com.github.shop.domain.shipment.ShipmentNotFoundException;
import com.github.shop.domain.shipment.ShipmentRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Cart cart = cartRepository.findById(orderDto.cartId()).orElseThrow(CartNotFoundException::new);
        Shipment shipment = shipmentRepository.findById(orderDto.shipmentId()).orElseThrow(ShipmentNotFoundException::new);
        Payment payment = paymentRepository.findById(orderDto.paymentId()).orElseThrow(PaymentNotFoundException::new);
        Order savedOrder = orderRepository.save(OrderMapper.createOrder(orderDto, cart, shipment, payment));
        saveOrderRows(cart, savedOrder.getId(), shipment);
        removeOrderCart(orderDto);
        emailService.sendEmail(savedOrder);
        return OrderMapper.createOrderSummary(savedOrder);
    }

    private void removeOrderCart(OrderDto orderDto) {
        cartItemRepository.deleteAllByCartId(orderDto.cartId());
        cartRepository.deleteCartById(orderDto.cartId());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(cart, orderId, shipment);
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> OderRowMapper.mapToOrderRow(orderId, cartItem))
                .peek(orderRowRepository::save)
                .forEach(log::info);
    }

    private void saveShipmentRow(Cart cart, Long orderId, Shipment shipment) {
        orderRowRepository.save(ShipmentMapper.mapToShipmentRow(cart, orderId, shipment));
    }
}
