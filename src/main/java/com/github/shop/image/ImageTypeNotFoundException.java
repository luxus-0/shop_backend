package com.github.shop.image;

public class ImageTypeNotFoundException extends Exception{
    public ImageTypeNotFoundException(){
        super("Image type not found");
    }
}
