package com.github.shop.infrastructure.security.login;

import com.github.shop.infrastructure.security.login.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

@AllArgsConstructor
@Log4j2
public class LoginUserDetailService implements UserDetailsService {

    private final LoginFacade loginFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
            UserDto user = loginFacade.findByUsername(username);
            return getUsers(user);
    }

    private User getUsers(UserDto user) {
        return new User(
                user.username(),
                user.password(),
                Collections.emptyList());
    }
}
