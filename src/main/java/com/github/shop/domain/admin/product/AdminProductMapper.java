package com.github.shop.domain.admin.product;

import com.github.shop.domain.admin.product.dto.AdminProductDto;
import com.github.shop.domain.image.ImageMapper;

import static com.github.shop.domain.slug.SlugifyMapper.slugify;

class AdminProductMapper {

    public static AdminProduct mapToAdminProduct(AdminProductDto adminProduct, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProduct.name())
                .categoryId(adminProduct.categoryId())
                .price(adminProduct.price())
                .currency(adminProduct.currency())
                .images(ImageMapper.mapToImages(adminProduct))
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
                .images(ImageMapper.mapToImages(adminProduct))
                .slug(slugify(adminProduct.slug()))
                .description(adminProduct.description())
                .fullDescription(adminProduct.fullDescription())
                .build();
    }
}
