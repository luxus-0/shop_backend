package com.github.Shop.cart.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SummaryDto(BigDecimal grossValue) {
}
