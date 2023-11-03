package com.github.Shop.order.dto;

import com.github.Shop.order.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record OrderSummary(Long id, LocalDateTime placeDate, OrderStatus status, BigDecimal grossValue) {
}
