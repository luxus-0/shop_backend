package com.github.Shop.adminorder.dto;

import com.github.Shop.order.OrderStatus;
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
