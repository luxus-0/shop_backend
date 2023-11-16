package com.github.shop.domain.admin.product;

import com.github.shop.domain.admin.product.dto.AdminProductDto;
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

    public AdminProduct findProducts(Long id) throws AdminProductNotFoundException {
        return adminProductRepository.findById(id)
                .orElseThrow(AdminProductNotFoundException::new);
    }

    AdminProduct createProduct(AdminProductDto adminProductDto) {
        return adminProductRepository.save(AdminProductMapper.mapToAdminProduct(adminProductDto));
    }


    public AdminProduct modificationProduct(AdminProductDto adminProductDto, Long id) {
        AdminProduct createdAdminProduct = AdminProductMapper.mapToAdminProduct(adminProductDto, id);
        return adminProductRepository.save(createdAdminProduct);
    }

    public void removeAdminProduct(Long id) {
        adminProductRepository.deleteById(id);
    }
}
