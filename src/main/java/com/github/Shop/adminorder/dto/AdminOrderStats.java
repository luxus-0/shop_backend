package com.github.Shop.adminorder.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record AdminOrderStats(List<Long> days, List<BigDecimal> sales, List<Long> orders) {
}
