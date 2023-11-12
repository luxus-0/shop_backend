package com.github.shop.product;

import com.github.shop.image.ImageNameNotFoundException;
import com.github.shop.image.ImagePathNotFoundException;
import com.github.shop.image.ImageTypeNotFoundException;
import com.github.shop.product.dto.ProductDto;

import static com.github.shop.image.ImageMapper.mapToImageDto;
import static com.github.shop.image.ImageMapper.mapToImages;
import static com.github.shop.review.ReviewMapper.mapToReviews;
import static com.github.shop.slug.SlugifyMapper.slugify;

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
