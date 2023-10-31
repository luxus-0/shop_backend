package com.github.Shop.product;

import com.github.Shop.product.dto.ProductDto;
import com.github.Shop.review.Review;
import com.github.Shop.review.dto.ReviewDto;
import com.github.slugify.Slugify;

import java.util.List;

class ProductMapper {
    public static Product mapToProduct(ProductDto productDto) {
        return Product.builder()
                .name(productDto.name())
                .categoryId(productDto.categoryId())
                .currency(productDto.currency())
                .description(productDto.description())
                .slug(slugify(productDto.slug()))
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
                .slug(slugify(productDto.slug()))
                .price(productDto.price())
                .images(productDto.images())
                .fullDescription(productDto.fullDescription())
                .build();
    }

    public static ProductDto mapToProductDto(Product product, List<Review>
            reviews) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryId(product.getCategoryId())
                .description(product.getDescription())
                .fullDescription(product.getFullDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .images(product.getImages())
                .slug(product.getSlug())
                .reviews(reviews.stream().map(review -> ReviewDto.builder()
                                .id(review.getId())
                                .productId(review.getProductId())
                                .authorName(review.getAuthorName())
                                .content(review.getContent())
                                .moderate(review.isModerated())
                                .build())
                        .toList())
                .build();
    }

    private static String slugify(String slug) {
        return Slugify.builder()
                .customReplacement("_","-")
                .build()
                .slugify(slug);
    }
}
