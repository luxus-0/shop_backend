package com.github.shop.infrastructure.security.register;

import com.github.shop.infrastructure.security.register.dto.RegisterUserDto;
import com.github.shop.infrastructure.security.register.dto.RegistrationResultDto;
import com.github.shop.infrastructure.security.register.exception.PasswordNotTheSameException;
import com.github.shop.infrastructure.security.register.exception.UserAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
public class RegisterController {

    private final RegisterFacade registerFacade;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResultDto> register(@RequestBody @Valid RegisterUserDto registerUser) throws PasswordNotTheSameException, UserAlreadyExistsException {
        RegistrationResultDto registerResult = registerFacade.register(
                new RegisterUserDto(registerUser.username(), registerUser.password(), registerUser.repeatPassword()));
        return ResponseEntity.status(CREATED).body(registerResult);
    }
}
