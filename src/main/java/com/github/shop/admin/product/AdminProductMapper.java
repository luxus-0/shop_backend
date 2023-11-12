package com.github.shop.admin.product;

import com.github.shop.admin.product.dto.AdminProductDto;
import com.github.shop.image.Image;
import com.github.slugify.Slugify;

import java.util.List;

import static com.github.shop.image.ImageMapper.mapToImages;
import static com.github.shop.slug.SlugifyMapper.slugify;

class AdminProductMapper {

    public static AdminProduct mapToAdminProduct(AdminProductDto adminProduct, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProduct.name())
                .categoryId(adminProduct.categoryId())
                .price(adminProduct.price())
                .currency(adminProduct.currency())
                .images(mapToImages(adminProduct))
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
                .images(mapToImages(adminProduct))
                .slug(slugify(adminProduct.slug()))
                .description(adminProduct.description())
                .fullDescription(adminProduct.fullDescription())
                .build();
    }
}
