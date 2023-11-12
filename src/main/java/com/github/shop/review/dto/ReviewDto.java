package com.github.shop.review.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ReviewDto {
    @NotNull
    private Long productId;
    @NotNull
    private String authorName;
    @NotNull
    private String content;
    @NotNull
    private boolean moderate;
}

