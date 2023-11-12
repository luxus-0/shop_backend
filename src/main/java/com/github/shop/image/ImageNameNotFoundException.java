package com.github.shop.image;

public class ImageNameNotFoundException extends Exception{
    public ImageNameNotFoundException(){
        super("Image name not found");
    }
}
