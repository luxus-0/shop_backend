package com.github.shop.admin.category.dto;

import com.github.shop.admin.category.Category;
import com.github.shop.product.Product;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<Product> products) {
}
