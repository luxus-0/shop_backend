package com.github.Shop.image.dto;

import lombok.Builder;

@Builder
public record ImageDto(String name, String type, String path) {
}
