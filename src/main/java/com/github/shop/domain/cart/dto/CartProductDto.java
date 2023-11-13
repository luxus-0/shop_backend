package com.github.shop.domain.cart.dto;

import lombok.Builder;

@Builder
public record CartProductDto(Long productId, int quantity) {
}
