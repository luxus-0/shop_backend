package com.github.Shop.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ProductNotFoundException extends Exception{
    Logger log = LoggerFactory.getLogger(ProductNotFoundException.class);
    ProductNotFoundException(){
        log.error("Product not found");
    }
}
