package com.github.Shop.product;

import com.github.Shop.product.dto.ProductDto;
import com.github.Shop.product.dto.ProductListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.Shop.product.ProductMapper.mapToProductDto;

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
        return mapToProductDto(productSaved);
    }

    public ProductDto modificationProduct(Product product, Long id) {
       Product createProduct = updateProduct(product, id);
        Product productSaved = productRepository.save(createProduct);
        return mapToProductDto(productSaved);
    }

    private Product updateProduct(Product product, Long id) {
        return Product.builder()
                .id(id)
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .currency(product.getCurrency())
                .image(product.getImage())
                .description(product.getDescription())
                .build();
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDto readProductBySlug(String slug) {
        return productRepository.findProductBySlug(slug);
    }
}
