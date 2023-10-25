package com.github.Shop.product;

import com.github.Shop.product.dto.ProductDto;
import com.github.Shop.product.dto.ProductListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProductManager {
    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    ProductListDto retrieveProduct() throws ProductNotFoundException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            throw new ProductNotFoundException();
        }
        return new ProductListDto(products);
    }

    Page<Product> retrieveProduct(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
       return productRepository.findAll(pageable);
    }

    ProductDto makeProduct(Product product){
        Product productSaved = productRepository.save(product);
        return new ProductDto(productSaved.getName(), productSaved.getCategory(), productSaved.getPrice(), productSaved.getCurrency(), productSaved.getDescription());
    }

    public ProductDto modificationProduct(Product product, Long id) {
       Product createProduct = Product.builder()
                .id(id)
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .currency(product.getCurrency())
                .description(product.getDescription())
                .build();
        Product productSaved = productRepository.save(createProduct);
        return new ProductDto(productSaved.getName(), productSaved.getCategory(), productSaved.getPrice(), productSaved.getCurrency(), productSaved.getDescription());
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }
}
