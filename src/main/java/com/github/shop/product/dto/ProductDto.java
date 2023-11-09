package com.github.shop.product.dto;

import com.github.shop.currency.Currency;
import com.github.shop.image.Image;
import com.github.shop.review.dto.ReviewDto;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductDto(Long id, String name, Long categoryId, BigDecimal price, Currency currency, Image image,
                         String slug, String description, String fullDescription, List<ReviewDto> reviews) {
}
