package com.github.shop.admin.product;

import com.github.shop.admin.product.dto.AdminProductDto;
import com.github.shop.image.Image;
import com.github.slugify.Slugify;

import java.util.List;

class AdminProductMapper {

    public static AdminProduct mapToAdminProduct(AdminProductDto adminProduct, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProduct.name())
                .categoryId(adminProduct.categoryId())
                .price(adminProduct.price())
                .currency(adminProduct.currency())
                .images(getImages(adminProduct))
                .slug(slugify(adminProduct.slug()))
                .description(adminProduct.description())
                .fullDescription(adminProduct.fullDescription())
                .build();
    }

    public static AdminProduct mapToAdminProduct(AdminProductDto adminProduct) {
        return AdminProduct.builder()
                .name(adminProduct.name())
                .categoryId(adminProduct.categoryId())
                .price(adminProduct.price())
                .currency(adminProduct.currency())
                .images(getImages(adminProduct))
                .slug(slugify(adminProduct.slug()))
                .description(adminProduct.description())
                .fullDescription(adminProduct.fullDescription())
                .build();
    }


    private static List<Image> getImages(AdminProductDto adminProduct) {
        return List.of(Image.builder()
                .name(adminProduct.image().name())
                .type(adminProduct.image().type())
                .path(adminProduct.image().path())
                .build());
    }

    private static String slugify(String slug) {
        return Slugify.builder()
                .customReplacement("_", "-")
                .build()
                .slugify(slug);
    }
}
