package com.github.Shop.admin;

import com.github.Shop.admin.dto.AdminProductDto;
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
        return AdminProductDto.builder()
                .name(adminProduct.getName())
                .category(adminProduct.getCategory())
                .price(adminProduct.getPrice())
                .currency(adminProduct.getCurrency())
                .image(adminProduct.getImage())
                .slug(slugify(adminProduct.getSlug()))
                .description(adminProduct.getDescription())
                .build();
    }

    private static String slugify(String slug) {
        return Slugify.builder()
                .customReplacement("_","-")
                .build()
                .slugify(slug);
    }
}
