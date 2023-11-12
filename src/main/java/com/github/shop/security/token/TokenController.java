package com.github.shop.security.token;

import com.github.shop.security.token.dto.JwtResponseDto;
import com.github.shop.security.token.dto.TokenRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;

    @PostMapping("/token")
    ResponseEntity<JwtResponseDto> authenticateAndGenerateToken(@Valid @RequestBody TokenRequestDto tokenRequest){
        final JwtResponseDto jwtResponseDto = jwtAuthenticatorFacade.authenticateAndGenerateToken(tokenRequest);
        return ResponseEntity.ok(jwtResponseDto);
    }
}
