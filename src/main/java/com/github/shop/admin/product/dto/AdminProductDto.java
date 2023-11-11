package com.github.shop.admin.product.dto;

import com.github.shop.currency.Currency;
import com.github.shop.image.dto.ImageDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AdminProductDto(@NotBlank String name,
                              @NotBlank Long categoryId,
                              @NotBlank BigDecimal price,
                              @NotBlank Currency currency,
                              @NotBlank ImageDto image,
                              @NotBlank String slug,
                              @NotBlank String description,
                              @NotBlank String fullDescription) {
}
