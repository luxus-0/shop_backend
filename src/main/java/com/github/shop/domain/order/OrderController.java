package com.github.shop.domain.order;

import com.github.shop.domain.cart.CartNotFoundException;
import com.github.shop.domain.mail.EmailNotFoundException;
import com.github.shop.domain.order.dto.InitOrder;
import com.github.shop.domain.order.dto.OrderDto;
import com.github.shop.domain.order.dto.OrderListDto;
import com.github.shop.domain.order.dto.OrderSummary;
import com.github.shop.domain.payment.PaymentNotFoundException;
import com.github.shop.domain.payment.PaymentService;
import com.github.shop.domain.shipment.ShipmentNotFoundException;
import com.github.shop.domain.shipment.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderManager orderManager;
    private final ShipmentService shipmentService;
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<OrderSummary> placeOrder(@RequestBody OrderDto orderDto, @AuthenticationPrincipal String username) throws ShipmentNotFoundException, PaymentNotFoundException, CartNotFoundException, EmailNotFoundException {
        return ResponseEntity.status(CREATED)
                .body(orderManager.placeOrder(orderDto, username));
    }

    @GetMapping("/init-order")
    public ResponseEntity<InitOrder> initOrder() {
        InitOrder initOrder = InitOrder.builder()
                .shipments(shipmentService.getShipments())
                .payments(paymentService.getPayments())
                .build();
        return ResponseEntity.ok(initOrder);
    }

    @GetMapping
    public ResponseEntity<List<OrderListDto>> getOrders(@AuthenticationPrincipal String username) {
        List<OrderListDto> orders = orderManager.getOrdersForCustomer(username);
        return ResponseEntity.ok(orders);
    }
}
