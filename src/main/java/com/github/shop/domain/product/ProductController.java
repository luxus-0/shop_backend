package com.github.shop.domain.product;

import com.github.shop.domain.product.dto.ProductDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.shop.domain.constant.Constants.REGEX_SLUG;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
class ProductController {
    private final ProductManager productManager;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() throws ProductNotFoundException {
        List<Product> findProducts = productManager.retrieveProducts();
        return new ResponseEntity<>(findProducts, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam(value = "page") int page,
                                                     @RequestParam(value = "size") int size) {
        var findProductByPage = productManager.retrieveProducts(page, size);
        return new ResponseEntity<>(findProductByPage, HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ProductDto> getProductBySlug(@PathVariable
                                                       @Pattern(regexp = REGEX_SLUG)
                                                       @Length(max = 255) String slug) {
        ProductDto findProductBySlug = productManager.readProductBySlug(slug);
        return new ResponseEntity<>(findProductBySlug, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> generateProduct(@RequestBody @Valid ProductDto productDto) {
        Product productSaved = productManager.makeProduct(productDto);
        return new ResponseEntity<>(productSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> modifyProduct(@RequestBody @Valid ProductDto productDto, @PathVariable Long id) {
        Product productSaved = productManager.modificationProduct(productDto, id);
        return new ResponseEntity<>(productSaved, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        productManager.removeProduct(id);
        return ResponseEntity.noContent().build();
    }
}
