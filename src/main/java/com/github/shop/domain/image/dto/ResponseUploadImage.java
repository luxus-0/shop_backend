package com.github.shop.domain.image.dto;

import lombok.Builder;

@Builder
public record ResponseUploadImage(String fileName) {
}
