package com.github.Shop.product;

import com.github.Shop.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.Shop.product.ProductMapper.mapToProduct;

@Service
class ProductManager {
    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    List<Product> retrieveProduct() throws ProductNotFoundException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            throw new ProductNotFoundException();
        }
        return products;
    }

    Page<Product> retrieveProduct(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
       return productRepository.findAll(pageable);
    }

    Product makeProduct(ProductDto productDto){
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

    public Product readProductBySlug(String slug) {
        return productRepository.findBySlug(slug)
                .orElseThrow();
    }
}
