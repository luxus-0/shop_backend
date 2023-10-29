package com.github.Shop.admin;

import com.github.Shop.admin.dto.AdminProductDto;
import com.github.Shop.image.Image;
import com.github.slugify.Slugify;

import java.util.List;

class AdminProductMapper {

    public static AdminProduct mapToAdminProduct(AdminProductDto adminProduct, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProduct.name())
                .price(adminProduct.price())
                .category(adminProduct.category())
                .currency(adminProduct.currency())
                .images(getImages(adminProduct, id))
                .slug(slugify(adminProduct.slug()))
                .description(adminProduct.description())
                .fullDescription(adminProduct.fullDescription())
                .build();
    }

    private static List<Image> getImages(AdminProductDto adminProduct, Long id) {
        return List.of(new Image(id,
                adminProduct.image().name(),
                adminProduct.image().type(),
                adminProduct.image().path()));
    }

    private static String slugify(String slug) {
        return Slugify.builder()
                .customReplacement("_","-")
                .build()
                .slugify(slug);
    }
}
