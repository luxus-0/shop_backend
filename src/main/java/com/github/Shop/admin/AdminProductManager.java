package com.github.Shop.admin;

import com.github.Shop.admin.dto.AdminProductDto;
import com.github.Shop.product.Product;
import com.github.Shop.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class AdminProductManager {

    private final AdminProductRepository adminProductRepository;
    public Page<AdminProduct> findProducts(Pageable pageable) {
        return adminProductRepository.findAll(pageable);
    }

    public List<AdminProduct> findProducts() {
        return adminProductRepository.findAll();
    }

    public AdminProductDto findProducts(Long id) throws AdminProductNotFoundException {
        return adminProductRepository.findById(id)
                .map(AdminProductMapper::mapToAdminProductDto)
                .orElseThrow(AdminProductNotFoundException::new);
    }

    AdminProductDto createProduct(AdminProduct adminProduct){
        AdminProduct adminProductSaved = adminProductRepository.save(adminProduct);
        return new AdminProductDto(adminProductSaved.getName(), adminProductSaved.getCategory(), adminProductSaved.getPrice(), adminProductSaved.getCurrency(), adminProductSaved.getDescription());
    }

    public AdminProductDto modificationProduct(AdminProduct adminProduct, Long id) {
        AdminProduct createProduct = AdminProduct.builder()
                .id(id)
                .name(adminProduct.getName())
                .price(adminProduct.getPrice())
                .category(adminProduct.getCategory())
                .currency(adminProduct.getCurrency())
                .description(adminProduct.getDescription())
                .build();
        AdminProduct productSaved = adminProductRepository.save(createProduct);
        return new AdminProductDto(productSaved.getName(), productSaved.getCategory(), productSaved.getPrice(), productSaved.getCurrency(), productSaved.getDescription());
    }

    public void removeAdminProduct(Long id) {
        adminProductRepository.deleteById(id);
    }
}
