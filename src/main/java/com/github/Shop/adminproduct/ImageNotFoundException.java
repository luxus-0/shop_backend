package com.github.Shop.adminproduct;

import lombok.extern.log4j.Log4j2;

@Log4j2
class ImageNotFoundException extends Exception{
    ImageNotFoundException(){
        log.error("Image not found");
    }
}
