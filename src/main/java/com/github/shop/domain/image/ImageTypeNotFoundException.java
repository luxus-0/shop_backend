package com.github.shop.domain.image;

public class ImageTypeNotFoundException extends Exception{
    public ImageTypeNotFoundException(){
        super("Image type not found");
    }
}
