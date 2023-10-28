package com.github.Shop.admin;

import lombok.extern.log4j.Log4j2;

@Log4j2
class ImageNotFoundException extends Exception{
    ImageNotFoundException(){
        log.error("Image not found");
    }
}
