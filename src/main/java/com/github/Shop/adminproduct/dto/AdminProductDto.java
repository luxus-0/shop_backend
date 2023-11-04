package com.github.Shop.adminproduct.dto;

import com.github.Shop.currency.Currency;
import com.github.Shop.image.dto.ImageDto;
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
