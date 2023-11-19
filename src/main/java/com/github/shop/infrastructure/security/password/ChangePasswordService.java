package com.github.shop.infrastructure.security.password;

import com.github.shop.infrastructure.security.login.LoginRepository;
import com.github.shop.infrastructure.security.login.User;
import com.github.shop.infrastructure.security.password.dto.ChangePasswordDto;
import com.github.shop.infrastructure.security.password.exception.IncorrectHashLinkException;
import com.github.shop.infrastructure.security.password.exception.LinkExpiredException;
import com.github.shop.infrastructure.security.register.exception.PasswordNotTheSameException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ChangePasswordService {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void changePassword(ChangePasswordDto changePassword) throws PasswordNotTheSameException, IncorrectHashLinkException, LinkExpiredException {
        if(!Objects.equals(changePassword.password(), changePassword.repeatPassword())){
            throw new PasswordNotTheSameException();
        }
        User user = loginRepository.findByHash(changePassword.hash())
                .orElseThrow(IncorrectHashLinkException::new);
        if(user.getHashDate().plusMinutes(10).isAfter(LocalDateTime.now())){
            user.setPassword("{bcrypt}" + passwordEncoder.encode(changePassword.password()));
            user.setHash(null);
            user.setHashDate(null);
        }
        else {
           throw new LinkExpiredException();
        }
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
