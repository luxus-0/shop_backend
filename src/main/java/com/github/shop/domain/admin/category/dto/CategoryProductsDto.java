package com.github.shop.domain.admin.category.dto;

import com.github.shop.domain.admin.category.Category;
import com.github.shop.domain.product.Product;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<Product> products) {
}
