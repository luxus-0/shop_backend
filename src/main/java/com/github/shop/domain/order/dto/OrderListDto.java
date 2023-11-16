package com.github.shop.domain.order.dto;

import com.github.shop.domain.order.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record OrderListDto(Long id, LocalDateTime placeDate, OrderStatus orderStatus, BigDecimal grossValue) {
}
