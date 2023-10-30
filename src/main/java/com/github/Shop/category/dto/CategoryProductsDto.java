package com.github.Shop.category.dto;

import com.github.Shop.category.Category;
import com.github.Shop.product.Product;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<Product> products) {
}
