package com.github.Shop.product;

import com.github.Shop.image.Image;
import com.github.Shop.image.dto.ImageDto;
import com.github.Shop.product.dto.ProductDto;
import com.github.slugify.Slugify;

class ProductMapper {
    public static ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .currency(product.getCurrency())
                .image(readImage(product))
                .slug(slugify(product.getSlug()))
                .description(product.getDescription())
                .build();
    }

    public static ImageDto readImage(Product product) {
        String name = readName(product);
        String type = readType(product);
        String path = readPath(product);
        return ImageDto.builder()
                .name(name)
                .type(type)
                .path(path)
                .build();
    }

    private static String readPath(Product product) {
        return product.getImage().stream()
                .map(Image::getPath)
                .findAny()
                .orElseThrow();
    }

    private static String readType(Product product) {
        return product.getImage().stream()
                .map(Image::getType)
                .findAny()
                .orElseThrow();
    }

    private static String readName(Product product) {
        return product.getImage().stream()
                .map(Image::getName)
                .findAny()
                .orElseThrow();
    }

    private static String slugify(String slug) {
        return Slugify.builder()
                .customReplacement("_","-")
                .build()
                .slugify(slug);
    }
}
