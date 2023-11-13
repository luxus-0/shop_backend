package com.github.shop.infrastructure.security.register;

import com.github.shop.infrastructure.security.login.LoginRepository;
import com.github.shop.infrastructure.security.login.User;
import com.github.shop.infrastructure.security.login.dto.RegisterUserDto;
import com.github.shop.infrastructure.security.login.dto.RegistrationResultDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegisterFacade {
    private final LoginRepository repository;
    public RegistrationResultDto register(RegisterUserDto registerUserDto){
        final User user = User.builder()
                .username(registerUserDto.username())
                .password(registerUserDto.password())
                .build();
        User savedUser = repository.save(user);
        return new RegistrationResultDto(savedUser.getId(), true, savedUser.getUsername());
    }
}
