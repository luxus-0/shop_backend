package com.github.Shop.product.dto;

import com.github.Shop.currency.Currency;

import java.math.BigDecimal;

public record ProductDto(String name, String category, BigDecimal price, Currency currency , String description) {
}
