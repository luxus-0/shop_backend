package com.github.shop.domain.cart.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SummaryDto(BigDecimal grossValue) {
}
