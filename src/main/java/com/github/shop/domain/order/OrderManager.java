package com.github.shop.domain.order;

import com.github.shop.domain.cart.Cart;
import com.github.shop.domain.cart.CartNotFoundException;
import com.github.shop.domain.cart.CartRepository;
import com.github.shop.domain.cartitem.CartItem;
import com.github.shop.domain.cartitem.CartItemRepository;
import com.github.shop.domain.mail.EmailNotFoundException;
import com.github.shop.domain.mail.EmailService;
import com.github.shop.domain.order.dto.OrderDto;
import com.github.shop.domain.order.dto.OrderListDto;
import com.github.shop.domain.order.dto.OrderSummary;
import com.github.shop.domain.orderrow.OrderRowManager;
import com.github.shop.domain.payment.Payment;
import com.github.shop.domain.payment.PaymentNotFoundException;
import com.github.shop.domain.payment.PaymentRepository;
import com.github.shop.domain.shipment.Shipment;
import com.github.shop.domain.shipment.ShipmentNotFoundException;
import com.github.shop.domain.shipment.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.github.shop.domain.customer.CustomerService.createCustomers;
import static com.github.shop.domain.order.OrderMapper.mapToOrderListDto;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderManager {

    private final OrderRepository orderRepository;
    private final OrderRowManager orderRowManager;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailService emailService;

    private static Order createOrder(OrderDto orderDto, Cart cart, Shipment shipment, Payment payment, Long userId) {
        return Order.builder()
                .customers(createCustomers(orderDto))
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculateGrossValue(cart.getItems(), shipment))
                .placeDate(orderDto.placeDate())
                .payment(payment)
                .userId(userId)
                .build();
    }

    private static BigDecimal calculateGrossValue(List<CartItem> items, Shipment shipment) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipment.getPrice());
    }

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto, Long userId) throws ShipmentNotFoundException, PaymentNotFoundException, CartNotFoundException, EmailNotFoundException {
        Cart cart = cartRepository.findById(orderDto.cartId()).orElseThrow(CartNotFoundException::new);
        Shipment shipment = shipmentRepository.findById(orderDto.shipmentId()).orElseThrow(ShipmentNotFoundException::new);
        Payment payment = paymentRepository.findById(orderDto.paymentId()).orElseThrow(PaymentNotFoundException::new);
        Order savedOrder = orderRepository.save(createOrder(orderDto, cart, shipment, payment, userId));
        orderRowManager.saveOrderRows(cart, savedOrder.getId(), shipment);
        removeOrderCart(orderDto);
        emailService.sendEmail(savedOrder);
        return OrderMapper.createOrderSummary(savedOrder);
    }

    public void removeOrderCart(OrderDto orderDto) {
        cartItemRepository.deleteAllByCartId(orderDto.cartId());
        cartRepository.deleteCartById(orderDto.cartId());
    }

    public List<OrderListDto> getOrdersForCustomer(Long userId) throws UserNotFoundException {
        if(userId == null){
            throw new UserNotFoundException();
        }
        return mapToOrderListDto(orderRepository.findByUserId(userId));
    }
}
