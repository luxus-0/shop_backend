package com.github.shop.adminorder.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AdminOrderStatsValue(AdminOrderStats statistics, BigDecimal totalValue, Long orderCount) {
}
