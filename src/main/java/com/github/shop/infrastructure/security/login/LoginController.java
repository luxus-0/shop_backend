package com.github.shop.infrastructure.security.login;

import com.github.shop.infrastructure.security.login.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
public class LoginController {

    private final LoginFacade loginFacade;
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody Login login) {
        UserDto user = loginFacade.findByUsername(login.username());
        return ResponseEntity.status(CREATED).body(user);
    }
}
