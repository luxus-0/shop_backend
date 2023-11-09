package com.github.shop.cart.dto;

import lombok.Builder;

@Builder
public record CartProductDto(Long productId, int quantity) {
}
