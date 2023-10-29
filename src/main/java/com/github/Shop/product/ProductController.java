package com.github.Shop.product;

import com.github.Shop.product.dto.ProductDto;
import com.github.Shop.product.dto.ProductListDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.Shop.constant.Constants.REGEX_SLUG;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/products")
class ProductController {
    private final ProductManager productManager;

    @GetMapping
    public ResponseEntity<List<Product>> uploadProducts() throws ProductNotFoundException {
        List<Product> findProducts = productManager.retrieveProduct();
        return new ResponseEntity<>(findProducts, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Product>> uploadProducts(@RequestParam(value = "page") int page,
                                                        @RequestParam(value = "size") int size){
        var findProductByPage = productManager.retrieveProduct(page, size);
        return new ResponseEntity<>(findProductByPage, HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Product> uploadProductBySlug(@PathVariable
                                                          @Pattern(regexp = REGEX_SLUG)
                                                          @Length(max = 255) String slug) {
        Product findProductBySlug = productManager.readProductBySlug(slug);
        return new ResponseEntity<>(findProductBySlug, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> generateProduct(@RequestBody @Valid Product product) {
        Product productSaved = productManager.makeProduct(product);
        return new ResponseEntity<>(productSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> modifyProduct(@RequestBody @Valid Product product, @PathVariable Long id) {
        Product productSaved = productManager.modificationProduct(product, id);
        return new ResponseEntity<>(productSaved, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        productManager.removeProduct(id);
        return ResponseEntity.noContent().build();
    }
}
