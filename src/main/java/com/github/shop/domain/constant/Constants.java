package com.github.shop.domain.constant;

public class Constants {
    public static final String REGEX_SLUG = "^[a-z0-9\\-]+";
    public static final String PHONE_REGEX = "\\+48 \\d{3} \\d{3} \\d{3}";
    public static final String[] HEADERS = {"Id", "firstname", "lastname", "pesel", "city", "street", "zipcode", "email", "phone", "Place Date", "Order Status", "Gross Value", "Payment"};
    public static final String USER_NOT_FOUND = "user not found";
    public static final String USER_ROLE_NOT_FOUND = "User role not found";
}
