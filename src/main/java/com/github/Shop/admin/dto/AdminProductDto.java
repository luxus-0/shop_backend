package com.github.Shop.admin.dto;

import com.github.Shop.currency.Currency;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AdminProductDto(String name,
                              String category,
                              BigDecimal price,
                              Currency currency,
                              String image,
                              String slug,
                              String description) {
}
