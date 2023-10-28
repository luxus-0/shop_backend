package com.github.Shop.admin.dto;

import com.github.Shop.currency.Currency;
import com.github.Shop.image.dto.ImageDto;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AdminProductDto(String name,
                              String category,
                              BigDecimal price,
                              Currency currency,
                              ImageDto image,
                              String slug,
                              String description) {
}
