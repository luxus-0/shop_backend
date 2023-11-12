package com.github.shop.admin.category.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CategoryDto {
    @NotNull
    String name;
    @NotNull
    String description;
    @NotNull
    String slug;
}
