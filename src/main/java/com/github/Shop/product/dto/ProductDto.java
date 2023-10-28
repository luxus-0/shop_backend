package com.github.Shop.product.dto;

import com.github.Shop.currency.Currency;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDto(String name, String category, BigDecimal price, Currency currency ,String image, String slug, String description) {
}
