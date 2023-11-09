package com.github.shop.order;

import com.github.shop.cart.Cart;
import com.github.shop.cart.CartNotFoundException;
import com.github.shop.cart.CartRepository;
import com.github.shop.cartitem.CartItemRepository;
import com.github.shop.mail.EmailService;
import com.github.shop.order.dto.OrderDto;
import com.github.shop.order.dto.OrderSummary;
import com.github.shop.orderrow.OrderRowRepository;
import com.github.shop.payment.Payment;
import com.github.shop.payment.PaymentNotFoundException;
import com.github.shop.payment.PaymentRepository;
import com.github.shop.shipment.Shipment;
import com.github.shop.shipment.ShipmentNotFoundException;
import com.github.shop.shipment.ShipmentRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.github.shop.order.OrderMapper.createOrder;
import static com.github.shop.order.OrderMapper.createOrderSummary;
import static com.github.shop.orderrow.OderRowMapper.mapToOrderRow;
import static com.github.shop.shipment.ShipmentMapper.mapToShipmentRow;

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
        Order savedOrder = orderRepository.save(createOrder(orderDto, cart, shipment, payment));
        saveOrderRows(cart, savedOrder.getId(), shipment);
        removeOrderCart(orderDto);
        emailService.sendEmail(savedOrder);
        return createOrderSummary(savedOrder);
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
                .map(cartItem -> mapToOrderRow(orderId, cartItem))
                .peek(orderRowRepository::save)
                .forEach(log::info);
    }

    private void saveShipmentRow(Cart cart, Long orderId, Shipment shipment) {
        orderRowRepository.save(mapToShipmentRow(cart, orderId, shipment));
    }
}
