package com.github.shop.security.login;

import com.github.shop.security.login.dto.RegisterUserDto;
import com.github.shop.security.login.dto.RegistrationResultDto;
import com.github.shop.security.login.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LoginAndRegisterFacade {

    private final LoginRepository repository;
    private static final String USER_NOT_FOUND = "user not found";

    public UserDto findByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getPassword()))
                .orElseThrow(() -> new BadCredentialsException(USER_NOT_FOUND));
    }

    public RegistrationResultDto register(RegisterUserDto registerUserDto){
        final User user = User.builder()
                .username(registerUserDto.username())
                .password(registerUserDto.password())
                .build();
        User savedUser = repository.save(user);
        return new RegistrationResultDto(savedUser.getId(), true, savedUser.getUsername());
    }
}
