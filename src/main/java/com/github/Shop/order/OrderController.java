package com.github.Shop.order;

import com.github.Shop.order.dto.OrderDto;
import com.github.Shop.order.dto.OrderSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderManager orderManager;

    @PostMapping
    public OrderSummary placeOrder(@RequestBody OrderDto orderDto){
        return orderManager.getOrder(orderDto);
    }
}
