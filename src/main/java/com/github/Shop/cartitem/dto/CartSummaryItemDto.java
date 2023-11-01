package com.github.Shop.cartitem.dto;

import com.github.Shop.product.dto.ProductDto;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CartSummaryItemDto(Long id, int quantity, ProductDto productDto, BigDecimal value) {
}
