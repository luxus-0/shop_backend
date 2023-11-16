package com.github.shop.infrastructure.security.login.dto.mapper;

import com.github.shop.infrastructure.security.login.User;
import com.github.shop.infrastructure.security.login.UserRole;
import com.github.shop.infrastructure.security.login.dto.UserDto;
import com.github.shop.infrastructure.security.login.exception.UserRoleNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleMapper {
    public static String getRole(UserDto user) {
        return user.roles().stream()
                .map(UserRole::getRole)
                .findAny()
                .orElseThrow(UserRoleNotFoundException::new);
    }

    public static List<UserRole> getRoles(User user) {
        return user.getAuthorities().stream()
                .map(userRole -> UserRole.valueOf(userRole.getAuthority()))
                .findAny()
                .map(List::of)
                .orElseThrow(UserRoleNotFoundException::new);
    }
}
