package com.github.shop.infrastructure.security.login;

import com.github.shop.infrastructure.security.login.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.github.shop.domain.constant.Constants.USER_NOT_FOUND;

@AllArgsConstructor
@Component
public class LoginFacade {

    private final LoginRepository repository;

    public UserDto findByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getPassword()))
                .orElseThrow(() -> new BadCredentialsException(USER_NOT_FOUND));
    }
}
