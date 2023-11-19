package com.github.shop.infrastructure.security.constant;

public class Constant {
    public static final String REGEX_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!#@$%^&*-?)(!]).{8,}$";
    public static final String HASH_LINK = "http://localhost:4200/lostPassword";
}
