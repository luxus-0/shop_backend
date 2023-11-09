package com.github.shop.product;

import com.github.shop.product.dto.ProductDto;
import com.github.shop.review.Review;
import com.github.shop.review.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.github.shop.product.ProductMapper.mapToProduct;
import static com.github.shop.product.ProductMapper.mapToProductDto;

@Service
@AllArgsConstructor
class ProductManager {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    List<Product> retrieveProduct() throws ProductNotFoundException {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return products;
    }

    Page<Product> retrieveProduct(int page, int size) {
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
        Product product = productRepository.findBySlug(slug).orElseThrow();
        List<Review> reviews = reviewRepository.findAllByProductIdAndModerated(product.getId(),
                true);
        return mapToProductDto(product, reviews);
    }
}
