package com.github.Shop.admin;

import com.github.Shop.admin.dto.AdminProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/admin/products")
class AdminProductController {

    private final AdminProductManager adminProductManager;

    @GetMapping
    public ResponseEntity<List<AdminProduct>> readProducts(){
        List<AdminProduct> adminProducts = adminProductManager.findProducts();
        return ResponseEntity.ok().body(adminProducts);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<AdminProduct>> readProducts(@RequestBody @Valid Pageable pageable){
        Page<AdminProduct> pageAdminProducts = adminProductManager.findProducts(pageable);
        return ResponseEntity.ok().body(pageAdminProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminProductDto> readProducts(@PathVariable Long id) throws AdminProductNotFoundException {
        AdminProductDto adminProduct = adminProductManager.findProducts(id);
        return ResponseEntity.ok().body(adminProduct);
    }

    @PostMapping
    public ResponseEntity<AdminProductDto> insertProduct(@RequestBody @Valid AdminProduct adminProduct) {
        AdminProductDto adminProductSavedDto = adminProductManager.createProduct(adminProduct);
        return new ResponseEntity<>(adminProductSavedDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminProductDto> modifyProduct(@RequestBody @Valid AdminProduct adminProduct, @PathVariable Long id) {
        AdminProductDto updateAdminProduct = adminProductManager.modificationProduct(adminProduct, id);
        return new ResponseEntity<>(updateAdminProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdminProductDto> deleteProduct(@PathVariable Long id) {
        adminProductManager.removeAdminProduct(id);
        return ResponseEntity.noContent().build();
    }


}
