package com.github.shop.adminorder.dto;

import com.github.shop.order.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public class AdminOrderDto {
    private Long id;
    private OrderStatus orderStatus;
    private LocalDateTime placeDate;
    private BigDecimal grossValue;
}
