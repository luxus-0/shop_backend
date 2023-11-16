package com.github.shop.domain.product;

import com.github.shop.domain.image.ImageNameNotFoundException;
import com.github.shop.domain.image.ImagePathNotFoundException;
import com.github.shop.domain.image.ImageTypeNotFoundException;
import com.github.shop.domain.product.dto.ProductDto;

import static com.github.shop.domain.image.ImageMapper.mapToImageDto;
import static com.github.shop.domain.image.ImageMapper.mapToImages;
import static com.github.shop.domain.review.ReviewMapper.mapToReviews;
import static com.github.shop.domain.slug.SlugifyMapper.slugify;

class ProductMapper {
    public static Product mapToProduct(ProductDto productDto) {
        return Product.builder()
                .name(productDto.name())
                .categoryId(productDto.categoryId())
                .currency(productDto.currency())
                .description(productDto.description())
                .slug(slugify(productDto.slug()))
                .price(productDto.price())
                .images(mapToImages(productDto))
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
                .images(mapToImages(productDto))
                .fullDescription(productDto.fullDescription())
                .build();
    }

    public static ProductDto mapToProductDto(Product product) throws ImageNameNotFoundException, ImageTypeNotFoundException, ImagePathNotFoundException {
        return ProductDto.builder()
                .name(product.getName())
                .categoryId(product.getCategoryId())
                .description(product.getDescription())
                .fullDescription(product.getFullDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .image(mapToImageDto(product))
                .slug(product.getSlug())
                .reviews(mapToReviews(product))
                .build();
    }
}
