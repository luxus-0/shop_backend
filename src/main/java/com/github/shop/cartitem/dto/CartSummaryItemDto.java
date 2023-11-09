package com.github.shop.cartitem.dto;

import com.github.shop.product.dto.ProductDto;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CartSummaryItemDto(Long id, int quantity, ProductDto productDto, BigDecimal value) {
}
