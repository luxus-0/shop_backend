package com.github.shop.infrastructure.security.login;

import com.github.shop.infrastructure.security.login.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.shop.domain.constant.Constants.USER_ROLE_NOT_FOUND;

@AllArgsConstructor
@Service
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
                List.of(new SimpleGrantedAuthority(getRole(user))));
    }

    private static String getRole(UserDto user) {
        return user.roles().stream()
                .map(UserRole::getRole)
                .findAny()
                .orElse(USER_ROLE_NOT_FOUND);
    }
}
