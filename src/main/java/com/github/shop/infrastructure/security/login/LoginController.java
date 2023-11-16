package com.github.shop.infrastructure.security.login;

import com.github.shop.infrastructure.security.token.JwtAuthenticatorFacade;
import com.github.shop.infrastructure.security.token.dto.JwtResponseDto;
import com.github.shop.infrastructure.security.token.dto.TokenRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
public class LoginController {

    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody TokenRequestDto tokenRequest) {
        JwtResponseDto jwtResponse = jwtAuthenticatorFacade.authenticateAndGenerateToken(tokenRequest);
        return ResponseEntity.status(CREATED).body(jwtResponse);
    }
}
