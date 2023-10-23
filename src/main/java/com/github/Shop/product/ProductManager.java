package com.github.Shop.product;

import com.github.Shop.product.dto.ProductDto;
import com.github.Shop.product.dto.ProductListDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductManager {
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

    ProductDto makeProduct(Product product){
        Product productSaved = productRepository.save(product);
        return new ProductDto(productSaved.getName(), productSaved.getCategory(), productSaved.getPrice(), productSaved.getCurrency(), productSaved.getDescription());
    }
}
