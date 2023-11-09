package com.github.shop.cart.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SummaryDto(BigDecimal grossValue) {
}
