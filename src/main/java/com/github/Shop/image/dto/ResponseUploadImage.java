package com.github.Shop.image.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Builder
@Entity
public record ResponseUploadImage(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                                  String path,
                                  String absolutePath,
                                  String name,
                                  String type,
                                  Long size,
                                  Long lastModified) {
}
