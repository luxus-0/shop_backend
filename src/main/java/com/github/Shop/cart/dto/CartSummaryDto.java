package com.github.Shop.cart.dto;

import com.github.Shop.cartitem.dto.CartSummaryItemDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public record CartSummaryDto(Long id, List<CartSummaryItemDto> items, SummaryDto summary) {
}
