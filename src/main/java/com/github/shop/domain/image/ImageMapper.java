package com.github.shop.domain.image;

import com.github.shop.domain.admin.product.dto.AdminProductDto;
import com.github.shop.domain.image.dto.ImageDto;
import com.github.shop.domain.product.Product;
import com.github.shop.domain.product.dto.ProductDto;

import java.util.List;

public class ImageMapper {
    public static ImageDto mapToImageDto(Product product) throws ImageNameNotFoundException, ImageTypeNotFoundException, ImagePathNotFoundException {
        return ImageDto.builder()
                .name(getName(product))
                .type(getType(product))
                .path(getPath(product))
                .build();
    }

    public static List<Image> mapToImages(ProductDto productDto) {
        return List.of(Image.builder()
                .name(productDto.image().name())
                .type(productDto.image().type())
                .path(productDto.image().path())
                .build());
    }

    public static List<Image> mapToImages(AdminProductDto adminProduct) {
        return List.of(Image.builder()
                .name(adminProduct.image().name())
                .type(adminProduct.image().type())
                .path(adminProduct.image().path())
                .build());
    }
    private static String getType(Product product) throws ImageTypeNotFoundException {
        return product.getImages().stream()
                .map(Image::getType)
                .findAny()
                .orElseThrow(ImageTypeNotFoundException::new);
    }

    private static String getName(Product product) throws ImageNameNotFoundException {
        return product.getImages().stream()
                .map(Image::getName)
                .findAny()
                .orElseThrow(ImageNameNotFoundException::new);
    }

    private static String getPath(Product product) throws ImagePathNotFoundException {
        return product.getImages().stream()
                .map(Image::getPath)
                .findAny()
                .orElseThrow(ImagePathNotFoundException::new);
    }
}
