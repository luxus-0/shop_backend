package com.github.shop.product.dto;

import com.github.shop.currency.Currency;
import com.github.shop.image.dto.ImageDto;
import com.github.shop.review.dto.ReviewDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductDto(@NotNull String name,
                         @NotNull Long categoryId,
                         @NotNull BigDecimal price,
                         @NotNull Currency currency,
                         @NotNull ImageDto image,
                         @NotNull String slug,
                         @NotNull String description,
                         @NotNull String fullDescription,
                         @NotNull List<ReviewDto> reviews) {
}
