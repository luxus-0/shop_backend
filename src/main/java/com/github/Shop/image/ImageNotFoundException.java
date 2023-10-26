package com.github.Shop.image;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ImageNotFoundException extends Exception {
    public ImageNotFoundException(){
        log.error("Image not found");
    }
}
