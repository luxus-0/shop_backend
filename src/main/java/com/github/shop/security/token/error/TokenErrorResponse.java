package com.github.shop.security.token.error;

import org.springframework.http.HttpStatus;

public record TokenErrorResponse(String message, HttpStatus httpStatus) {
}
