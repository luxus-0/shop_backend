package com.github.Shop.product;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Log4j2
class ProductNotFoundException extends Exception{
    ProductNotFoundException(){
        log.error("Product not found");
    }
}
