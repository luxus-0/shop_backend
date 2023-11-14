package com.github.shop.infrastructure.security.register;

import com.github.shop.infrastructure.security.login.LoginRepository;
import com.github.shop.infrastructure.security.login.User;
import com.github.shop.infrastructure.security.login.UserRole;
import com.github.shop.infrastructure.security.register.dto.RegisterUserDto;
import com.github.shop.infrastructure.security.register.dto.RegistrationResultDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RegisterFacade {
    private final LoginRepository repository;
    private final PasswordEncoder bCryptPasswordEncoder;
    public RegistrationResultDto register(RegisterUserDto registerUser){
        final User user = User.builder()
                .username(registerUser.username())
                .password(bCryptPasswordEncoder.encode(registerUser.password()))
                .authorities(List.of(UserRole.ROLE_CUSTOMER))
                .build();
        User savedUser = repository.save(user);
        return new RegistrationResultDto(savedUser.getId(), true, savedUser.getUsername());
    }
}
