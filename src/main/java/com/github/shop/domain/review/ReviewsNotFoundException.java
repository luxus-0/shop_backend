package com.github.shop.domain.review;

public class ReviewsNotFoundException extends Exception{
    public ReviewsNotFoundException(){
        super("Review not found");
    }
}
