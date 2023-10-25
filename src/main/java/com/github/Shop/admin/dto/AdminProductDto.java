package com.github.Shop.admin.dto;

import java.math.BigDecimal;

public record AdminProductDto(String name, String category, BigDecimal price, String currency, String description) {
}
