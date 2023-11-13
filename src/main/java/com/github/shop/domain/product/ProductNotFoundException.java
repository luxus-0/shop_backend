package com.github.shop.domain.product;

import lombok.extern.log4j.Log4j2;

@Log4j2
class ProductNotFoundException extends Exception {
    ProductNotFoundException() {
        log.error("Product not found");
    }
}
