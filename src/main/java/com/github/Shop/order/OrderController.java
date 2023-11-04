package com.github.Shop.order;

import com.github.Shop.order.dto.OrderDto;
import com.github.Shop.order.dto.OrderSummary;
import com.github.Shop.shipment.ShipmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderManager orderManager;
    @PostMapping
    public ResponseEntity<OrderSummary> placeOrder(@RequestBody OrderDto orderDto) throws ShipmentNotFoundException {
        return ResponseEntity.status(CREATED)
                .body(orderManager.getOrder(orderDto));
    }
}
