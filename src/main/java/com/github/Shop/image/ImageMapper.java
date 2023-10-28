package com.github.Shop.image;

import com.github.Shop.image.dto.ImageDto;

public class ImageMapper {
    public static ImageDto mapToImageDto(Image image){
        return ImageDto.builder()
                .name(image.getName())
                .type(image.getType())
                .path(image.getPath())
                .build();
    }
}
