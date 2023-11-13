package com.github.shop.domain.product;

import com.github.shop.domain.product.dto.ProductDto;
import com.github.shop.domain.image.ImageNameNotFoundException;
import com.github.shop.domain.image.ImagePathNotFoundException;
import com.github.shop.domain.image.ImageTypeNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.github.shop.domain.product.ProductMapper.mapToProduct;
import static com.github.shop.domain.product.ProductMapper.mapToProductDto;

@Service
@AllArgsConstructor
@Log4j2
class ProductManager {
    private final ProductRepository productRepository;

    List<Product> retrieveProducts() throws ProductNotFoundException {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .findAny()
                .map(List::of)
                .orElseThrow(ProductNotFoundException::new);
    }

    Page<Product> retrieveProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    Product makeProduct(ProductDto productDto) {
        return productRepository.save(mapToProduct(productDto));
    }

    public Product modificationProduct(ProductDto productDto, Long id) {
        Product createProduct = updateProduct(productDto, id);
        return productRepository.save(createProduct);
    }

    private Product updateProduct(ProductDto productDto, Long id) {
        return mapToProduct(productDto, id);
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ProductDto readProductBySlug(String slug) {
        try {
            Product product = productRepository.findBySlug(slug).orElseThrow(ProductNotFoundException::new);
            return mapToProductDto(product);
        } catch (ProductNotFoundException | ImageTypeNotFoundException | ImagePathNotFoundException |
                 ImageNameNotFoundException e) {
            log.error(e.getMessage());
        }
        return ProductDto.builder().build();
    }


}
