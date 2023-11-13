package com.github.shop.domain.image;

public class ImageNameNotFoundException extends Exception{
    public ImageNameNotFoundException(){
        super("Image name not found");
    }
}
