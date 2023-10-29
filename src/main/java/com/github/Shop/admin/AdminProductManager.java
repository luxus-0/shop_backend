package com.github.Shop.admin;

import com.github.Shop.admin.dto.AdminProductDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.Shop.admin.AdminProductMapper.mapToAdminProduct;

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

    public AdminProduct findProducts(Long id) throws AdminProductNotFoundException {
        return adminProductRepository.findById(id)
                .orElseThrow(AdminProductNotFoundException::new);
    }

    AdminProduct createProduct(AdminProduct adminProduct) {
        AdminProduct createAdminProduct = AdminProduct.builder()
                .name(adminProduct.getName())
                .category(adminProduct.getCategory())
                .currency(adminProduct.getCurrency())
                .price(adminProduct.getPrice())
                .slug(adminProduct.getSlug())
                .images(adminProduct.getImages())
                .description(adminProduct.getDescription())
                .fullDescription(adminProduct.getFullDescription())
                .build();
        return adminProductRepository.save(createAdminProduct);
    }


    public AdminProduct modificationProduct(AdminProductDto adminProduct, Long id) {
        AdminProduct createdAdminProduct = mapToAdminProduct(adminProduct, id);
        return adminProductRepository.save(createdAdminProduct);
    }

    public void removeAdminProduct(Long id) {
        adminProductRepository.deleteById(id);
    }
}
