package com.github.shop.domain.image;

public class ImagePathNotFoundException extends Exception{
    public ImagePathNotFoundException(){
        super("Image path not found");
    }
}
