package com.github.shop.infrastructure.security.token.error;

import org.springframework.http.HttpStatus;

public record TokenErrorResponse(String message, HttpStatus httpStatus) {
}
