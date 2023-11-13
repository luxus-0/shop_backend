package com.github.shop.infrastructure.security.token;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("auth.jwt")
public record JwtConfigurationProperties(String secret, long expirationDays, String issuer) {
}
