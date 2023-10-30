package com.github.Shop.product.dto;

import com.github.Shop.currency.Currency;
import com.github.Shop.image.Image;
import com.github.Shop.image.dto.ImageDto;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductDto(String name, Long categoryId, BigDecimal price, Currency currency , List<Image> images, String slug, String description, String fullDescription) {
}
