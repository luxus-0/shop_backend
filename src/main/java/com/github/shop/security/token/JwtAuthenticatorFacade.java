package com.github.shop.security.token;

import com.github.shop.security.token.dto.JwtResponseDto;
import com.github.shop.security.token.dto.TokenRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class JwtAuthenticatorFacade {

    private final AuthenticationManager authenticationManager;
    public JwtResponseDto authenticateAndGenerateToken(TokenRequestDto tokenRequest) {
        Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(tokenRequest.username(), tokenRequest.password())
        );
        return JwtResponseDto.builder().build();
    }
}