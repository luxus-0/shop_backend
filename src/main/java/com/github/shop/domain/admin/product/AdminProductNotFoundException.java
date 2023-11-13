package com.github.shop.domain.admin.product;

import lombok.extern.log4j.Log4j2;

@Log4j2
class AdminProductNotFoundException extends Exception {
    AdminProductNotFoundException() {
        log.error("Admin product not found");
    }
}
