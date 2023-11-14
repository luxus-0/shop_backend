package com.github.shop.infrastructure.security.login.dto;

import com.github.shop.infrastructure.security.login.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import static com.github.shop.infrastructure.security.constant.Constant.REGEX_PASSWORD;

public record UserDto(Long id,
                      @Email @NotBlank(message = "${not.blank.username}")
                      String username,
                      @Pattern(regexp = REGEX_PASSWORD) @NotBlank(message = "{not.blank.password}")
                      String password,
                      List<UserRole> roles) {
}
