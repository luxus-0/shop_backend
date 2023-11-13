package com.github.shop.domain.review;

import com.github.shop.domain.product.Product;
import com.github.shop.domain.review.dto.ReviewDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ReviewMapper {

    public static List<ReviewDto> mapToReviews(Product product) {
        return product.getReviews().stream()
                .map(ReviewMapper::mapToReviewDto)
                .toList();
    }

    private static ReviewDto mapToReviewDto(Review review) {
        return ReviewDto.builder()
                .productId(review.getProductId())
                .authorName(review.getAuthorName())
                .content(review.getContent())
                .moderate(review.isModerated())
                .build();
    }
}
