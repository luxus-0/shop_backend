package com.github.shop.order;

import com.github.shop.cart.CartNotFoundException;
import com.github.shop.order.dto.OrderDto;
import com.github.shop.order.dto.OrderSummary;
import com.github.shop.payment.PaymentNotFoundException;
import com.github.shop.shipment.ShipmentNotFoundException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderManager orderManager;

    @PostMapping
    public ResponseEntity<OrderSummary> placeOrder(@RequestBody OrderDto orderDto) throws ShipmentNotFoundException, PaymentNotFoundException, MessagingException, CartNotFoundException {
        return ResponseEntity.status(CREATED)
                .body(orderManager.getOrder(orderDto));
    }
}
