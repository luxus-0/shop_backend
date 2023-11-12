package com.github.shop.review;

public class ReviewsNotFoundException extends Exception{
    public ReviewsNotFoundException(){
        super("Review not found");
    }
}
