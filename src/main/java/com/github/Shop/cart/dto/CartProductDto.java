package com.github.Shop.cart.dto;

import lombok.Builder;

@Builder
public record CartProductDto(Long ProductId, int quantity) {
}
