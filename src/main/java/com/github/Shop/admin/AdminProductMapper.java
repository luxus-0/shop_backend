package com.github.Shop.admin;

import com.github.Shop.admin.dto.AdminProductDto;

class AdminProductMapper {
    public static AdminProductDto mapToAdminProductDto(AdminProduct adminProduct) {
        return new AdminProductDto(adminProduct.getName(), adminProduct.getCategory(), adminProduct.getPrice(), adminProduct.getCurrency(), adminProduct.getDescription());
    }
}
