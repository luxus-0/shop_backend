package com.github.Shop.adminproduct.dto;

import com.github.Shop.currency.Currency;
import com.github.Shop.image.dto.ImageDto;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AdminProductDto(String name,
                              Long categoryId,
                              BigDecimal price,
                              Currency currency,
                              ImageDto image,
                              String slug,
                              String description,
                              String fullDescription) {
}
