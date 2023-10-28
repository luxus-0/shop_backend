package com.github.Shop.admin;

import com.github.Shop.admin.dto.AdminProductDto;
import com.github.Shop.image.ImageMapper;
import com.github.Shop.image.dto.ImageDto;
import com.github.slugify.Slugify;

class AdminProductMapper {

    public static AdminProduct mapToAdminProduct(AdminProduct adminProduct, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProduct.getName())
                .price(adminProduct.getPrice())
                .category(adminProduct.getCategory())
                .currency(adminProduct.getCurrency())
                .image(adminProduct.getImage())
                .slug(slugify(adminProduct.getSlug()))
                .description(adminProduct.getDescription())
                .build();
    }

    public static AdminProductDto mapToAdminProductDto(AdminProduct adminProduct) {
        try {
            return AdminProductDto.builder()
                    .name(adminProduct.getName())
                    .category(adminProduct.getCategory())
                    .price(adminProduct.getPrice())
                    .currency(adminProduct.getCurrency())
                    .image(readImage(adminProduct))
                    .slug(slugify(adminProduct.getSlug()))
                    .description(adminProduct.getDescription())
                    .build();
        } catch (ImageNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
        public static ImageDto readImage(AdminProduct adminProduct) throws ImageNotFoundException {
            return adminProduct.getImage().stream()
                    .map(ImageMapper::mapToImageDto)
                    .findAny()
                    .orElseThrow(ImageNotFoundException::new);
    }

    private static String slugify(String slug) {
        return Slugify.builder()
                .customReplacement("_","-")
                .build()
                .slugify(slug);
    }
}
