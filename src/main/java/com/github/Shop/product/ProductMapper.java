package com.github.Shop.product;

import com.github.Shop.product.dto.ProductDto;
import com.github.slugify.Slugify;

class ProductMapper {
    public static Product mapToProduct(ProductDto productDto) {
        return Product.builder()
                .name(productDto.name())
                .categoryId(productDto.categoryId())
                .currency(productDto.currency())
                .description(productDto.description())
                .slug(productDto.slug())
                .price(productDto.price())
                .images(productDto.images())
                .fullDescription(productDto.fullDescription())
                .build();
    }

    public static Product mapToProduct(ProductDto productDto, Long id) {
        return Product.builder()
                .id(id)
                .name(productDto.name())
                .categoryId(productDto.categoryId())
                .currency(productDto.currency())
                .description(productDto.description())
                .slug(productDto.slug())
                .price(productDto.price())
                .images(productDto.images())
                .fullDescription(productDto.fullDescription())
                .build();
    }

    private static String slugify(String slug) {
        return Slugify.builder()
                .customReplacement("_","-")
                .build()
                .slugify(slug);
    }
}
