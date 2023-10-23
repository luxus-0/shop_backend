package com.github.Shop.product.dto;

import java.math.BigDecimal;

public record ProductDto(String name, String category, BigDecimal price, String currency ,String description) {
}
