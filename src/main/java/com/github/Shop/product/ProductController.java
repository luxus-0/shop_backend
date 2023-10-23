package com.github.Shop.product;

import com.github.Shop.product.dto.ProductDto;
import com.github.Shop.product.dto.ProductListDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductManager productManager;

    @GetMapping("/products")
    public ResponseEntity<ProductListDto> readProducts() throws ProductNotFoundException {
        ProductListDto findProductsDto = productManager.retrieveProduct();
        return new ResponseEntity<>(findProductsDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> generateProduct(@RequestBody Product product) {
        ProductDto productSavedDto = productManager.makeProduct(product);
        return new ResponseEntity<>(productSavedDto, HttpStatus.CREATED);
    }

}
