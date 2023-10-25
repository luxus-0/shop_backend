package com.github.Shop.product;

import com.github.Shop.product.dto.ProductDto;
import com.github.Shop.product.dto.ProductListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/products")
class ProductController {
    private final ProductManager productManager;

    @GetMapping
    public ResponseEntity<ProductListDto> uploadProducts() throws ProductNotFoundException {
        ProductListDto findProductsDto = productManager.retrieveProduct();
        return new ResponseEntity<>(findProductsDto, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Product>> uploadProducts(@RequestParam(value = "page") int page,
                                                        @RequestParam(value = "size") int size){
        Page<Product> findProductByPage = productManager.retrieveProduct(page, size);
        return new ResponseEntity<>(findProductByPage, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> generateProduct(@RequestBody Product product) {
        ProductDto productSavedDto = productManager.makeProduct(product);
        return new ResponseEntity<>(productSavedDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> modifyProduct(@RequestBody Product product, @PathVariable Long id) {
        ProductDto productSavedDto = productManager.modificationProduct(product, id);
        return new ResponseEntity<>(productSavedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) {
        productManager.removeProduct(id);
        return ResponseEntity.noContent().build();
    }
}
