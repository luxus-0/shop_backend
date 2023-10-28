package com.github.Shop.admin;

import com.github.Shop.admin.dto.AdminProductDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.Shop.admin.AdminProductMapper.mapToAdminProduct;
import static com.github.Shop.admin.AdminProductMapper.mapToAdminProductDto;

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
        return mapToAdminProductDto(adminProductSaved);
    }

    public AdminProductDto modificationProduct(AdminProduct adminProduct, Long id) {
        AdminProduct createdAdminProduct = mapToAdminProduct(adminProduct, id);
        AdminProduct productSaved = adminProductRepository.save(createdAdminProduct);
        return mapToAdminProductDto(productSaved);
    }

    public void removeAdminProduct(Long id) {
        adminProductRepository.deleteById(id);
    }
}
