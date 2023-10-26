package com.github.Shop.image.dto;

import lombok.Builder;

@Builder
public record ResponseUploadImage(String path, String name, String type, Long size, Long lastModified) {
}
