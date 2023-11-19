package com.github.shop.infrastructure.security.password;

import com.github.shop.infrastructure.security.password.dto.ChangePasswordDto;
import com.github.shop.infrastructure.security.password.dto.EmailRequestDto;
import com.github.shop.infrastructure.security.password.exception.EmailNotExistsException;
import com.github.shop.infrastructure.security.password.exception.IncorrectHashLinkException;
import com.github.shop.infrastructure.security.password.exception.LinkExpiredException;
import com.github.shop.infrastructure.security.register.exception.PasswordNotTheSameException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PasswordController {
    private final LostPasswordService lostPasswordService;
    private final ChangePasswordService changePasswordService;

    @PostMapping("/lostPassword")
    public void lostPassword(@RequestBody @Valid EmailRequestDto email) throws EmailNotExistsException {
        lostPasswordService.sendLostPasswordLink(email);
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody @Valid ChangePasswordDto changePassword) throws LinkExpiredException, IncorrectHashLinkException, PasswordNotTheSameException {
        changePasswordService.changePassword(changePassword);
    }
}
