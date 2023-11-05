package com.github.Shop.adminproduct;

import com.github.Shop.adminproduct.dto.AdminProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/admin/products")
class AdminProductController {

    private final AdminProductManager adminProductManager;

    @GetMapping
    public ResponseEntity<List<AdminProduct>> readProducts() {
        List<AdminProduct> adminProducts = adminProductManager.findProducts();
        return ResponseEntity.ok().body(adminProducts);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<AdminProduct>> readProducts(@RequestBody @Valid Pageable pageable) {
        Page<AdminProduct> pageAdminProducts = adminProductManager.findProducts(pageable);
        return ResponseEntity.ok().body(pageAdminProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminProduct> readProducts(@PathVariable Long id) throws AdminProductNotFoundException {
        AdminProduct adminProduct = adminProductManager.findProducts(id);
        return ResponseEntity.ok().body(adminProduct);
    }

    @PostMapping
    public ResponseEntity<AdminProduct> insertProduct(@RequestBody @Valid AdminProductDto adminProductDto) {
        AdminProduct adminProductSaved = adminProductManager.createProduct(adminProductDto);
        return new ResponseEntity<>(adminProductSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminProduct> modifyProduct(@RequestBody @Valid AdminProductDto adminProduct, @PathVariable Long id) {
        AdminProduct updateAdminProduct = adminProductManager.modificationProduct(adminProduct, id);
        return new ResponseEntity<>(updateAdminProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdminProduct> deleteProduct(@PathVariable Long id) {
        adminProductManager.removeAdminProduct(id);
        return ResponseEntity.noContent().build();
    }


}
