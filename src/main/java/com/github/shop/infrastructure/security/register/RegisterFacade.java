package com.github.shop.infrastructure.security.register;

import com.github.shop.infrastructure.security.login.LoginRepository;
import com.github.shop.infrastructure.security.login.User;
import com.github.shop.infrastructure.security.login.UserRole;
import com.github.shop.infrastructure.security.register.dto.RegisterUserDto;
import com.github.shop.infrastructure.security.register.dto.RegistrationResultDto;
import com.github.shop.infrastructure.security.register.exception.PasswordNotTheSameException;
import com.github.shop.infrastructure.security.token.JwtAuthenticatorFacade;
import com.github.shop.infrastructure.security.token.dto.JwtResponseDto;
import com.github.shop.infrastructure.security.token.dto.TokenRequestDto;
import com.github.shop.infrastructure.security.validation.ValidateRegistration;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
public class RegisterFacade {
    private final LoginRepository repository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final ValidateRegistration validator;
    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;

    public RegistrationResultDto register(RegisterUserDto registerUser) throws PasswordNotTheSameException, UserAlreadyExistsException {
       validator.validateRegistration(registerUser);
        final User user = User.builder()
                .username(registerUser.username())
                .password(bCryptPasswordEncoder.encode(registerUser.password()))
                .created(true)
                .authorities(List.of(UserRole.ROLE_CUSTOMER))
                .build();
        User savedUser = repository.save(user);
        JwtResponseDto jwt = jwtAuthenticatorFacade.authenticateAndGenerateToken(new TokenRequestDto(savedUser.getUsername(), savedUser.getPassword()));
        return RegistrationResultDto.builder()
                .id(savedUser.getId())
                .username(jwt.username())
                .created(savedUser.isCreated())
                .token(jwt.token())
                .build();
    }
}
