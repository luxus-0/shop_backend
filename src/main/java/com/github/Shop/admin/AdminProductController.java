package com.github.Shop.admin;

import com.github.Shop.admin.dto.AdminProductDto;
import com.github.Shop.product.dto.ProductDto;
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
@RequestMapping("api/v1/admin/products")
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
