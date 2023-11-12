package com.github.shop.slug;

import com.github.slugify.Slugify;

public class SlugifyMapper {
    public static String slugify(String slug) {
        return Slugify.builder()
                .customReplacement("_", "-")
                .build()
                .slugify(slug);
    }
}
