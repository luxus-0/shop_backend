package com.github.shop.infrastructure.security.login;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsUserByUsername(String username);
}
