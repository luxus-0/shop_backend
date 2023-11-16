package com.github.shop.infrastructure.security.validation;

import com.github.shop.infrastructure.security.login.LoginRepository;
import com.github.shop.infrastructure.security.register.dto.RegisterUserDto;
import com.github.shop.infrastructure.security.register.exception.PasswordNotTheSameException;
import com.github.shop.infrastructure.security.register.exception.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidateRegistration {

    private final LoginRepository loginRepository;

    public void validateRegistration(RegisterUserDto registerUser) throws PasswordNotTheSameException, UserAlreadyExistsException {
        validatePassword(registerUser);
        validateUsernameAvailability(registerUser.username());
    }

    private void validatePassword(RegisterUserDto registerUser) throws PasswordNotTheSameException {
        if (!registerUser.password().equals(registerUser.repeatPassword())) {
            throw new PasswordNotTheSameException();
        }
    }

    private void validateUsernameAvailability(String username) throws UserAlreadyExistsException {
        if (loginRepository.existsUserByUsername(username)) {
            throw new UserAlreadyExistsException(username);
        }
    }
}
