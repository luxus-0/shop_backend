package com.github.shop.image;

public class ImagePathNotFoundException extends Exception{
    public ImagePathNotFoundException(){
        super("Image path not found");
    }
}
