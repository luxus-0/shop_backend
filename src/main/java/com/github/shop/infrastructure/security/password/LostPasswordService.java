package com.github.shop.infrastructure.security.password;

import com.github.shop.domain.mail.EmailService;
import com.github.shop.domain.mail.dto.EmailDto;
import com.github.shop.infrastructure.security.login.LoginRepository;
import com.github.shop.infrastructure.security.login.User;
import com.github.shop.infrastructure.security.password.dto.EmailRequestDto;
import com.github.shop.infrastructure.security.password.exception.EmailNotExistsException;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.github.shop.infrastructure.security.constant.Constant.HASH_LINK;

@Service
@AllArgsConstructor
public class LostPasswordService {

    private final EmailService emailService;
    private final LoginRepository loginRepository;

    @Transactional
    public void sendLostPasswordLink(EmailRequestDto email) throws EmailNotExistsException {
        User user = loginRepository.findByUsername(email.email())
                .orElseThrow(EmailNotExistsException::new);

        String hash = generateHashForLostPassword(user);

        user.setHash(hash);
        user.setHashDate(LocalDateTime.now());

        EmailDto emailDto = new EmailDto(
                email.email(),
                "Reset password",
                createMessage(createLink(hash)));

        emailService.send(emailDto);
    }

    private String generateHashForLostPassword(User user) {
        String toHash = user.getId() + user.getUsername() + user.getPassword() + LocalDateTime.now();
        return DigestUtils.sha256Hex(toHash);
    }

    private String createMessage(String hashLink) {
        return String.format(
                "To reset your password, click on the following link:\\n" +
                        "%s\\n Thank you",
                hashLink
        );
    }

    private String createLink(String hash) {
        return HASH_LINK + hash;
    }
}
