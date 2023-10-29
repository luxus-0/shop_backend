package com.github.Shop.image.dto;

import com.github.Shop.image.Image;
import lombok.Builder;

@Builder
public record ResponseUploadImage(String fileName) {
}
