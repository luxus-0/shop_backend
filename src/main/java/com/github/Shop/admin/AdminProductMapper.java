package com.github.Shop.admin;

import com.github.Shop.admin.dto.AdminProductDto;

class AdminProductMapper {

    public static AdminProduct mapToAdminProduct(AdminProduct adminProduct, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProduct.getName())
                .price(adminProduct.getPrice())
                .category(adminProduct.getCategory())
                .currency(adminProduct.getCurrency())
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
                .description(adminProduct.getDescription())
                .build();
    }
}
