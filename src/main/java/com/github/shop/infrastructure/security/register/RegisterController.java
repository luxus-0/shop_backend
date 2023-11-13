package com.github.shop.infrastructure.security.register;

import com.github.shop.infrastructure.security.login.dto.RegisterUserDto;
import com.github.shop.infrastructure.security.login.dto.RegistrationResultDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegisterController {

    private final RegisterFacade registerFacade;
    private final PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResultDto> register(@RequestBody RegisterUserDto registerUser){
        String encodedPassword = bCryptPasswordEncoder.encode(registerUser.password());
        RegistrationResultDto registerResult = registerFacade.register(
                new RegisterUserDto(registerUser.username(), encodedPassword));
        return ResponseEntity.ok(registerResult);
    }
}
