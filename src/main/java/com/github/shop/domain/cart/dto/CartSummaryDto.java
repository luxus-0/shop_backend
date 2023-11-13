package com.github.shop.domain.cart.dto;

import com.github.shop.domain.cartitem.dto.CartSummaryItemDto;
import lombok.Builder;

import java.util.List;

@Builder
public record CartSummaryDto(Long id, List<CartSummaryItemDto> items, SummaryDto summary) {
}
