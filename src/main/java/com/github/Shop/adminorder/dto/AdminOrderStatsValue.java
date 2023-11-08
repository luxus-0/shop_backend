package com.github.Shop.adminorder.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AdminOrderStatsValue(AdminOrderStats statistics, BigDecimal totalValue, Long orderCount) {
}
