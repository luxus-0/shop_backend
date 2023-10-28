package com.github.Shop.product;

import com.github.Shop.product.dto.ProductDto;
import com.github.slugify.Slugify;

class ProductMapper {
    public static ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .currency(product.getCurrency())
                .image(product.getImage())
                .slug(slugify(product.getSlug()))
                .description(product.getDescription())
                .build();
    }

    public static Product mapToProduct(ProductDto productDto) {
        return Product.builder()
                .name(productDto.name())
                .price(productDto.price())
                .category(productDto.category())
                .currency(productDto.currency())
                .image(productDto.image())
                .slug(slugify(productDto.slug()))
                .description(productDto.description())
                .build();
    }

    private static String slugify(String slug) {
        return Slugify.builder()
                .customReplacement("_","-")
                .build()
                .slugify(slug);
    }
}
