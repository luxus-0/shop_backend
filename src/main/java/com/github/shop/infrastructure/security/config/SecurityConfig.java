package com.github.shop.infrastructure.security.config;

import com.github.shop.infrastructure.security.login.LoginFacade;
import com.github.shop.infrastructure.security.login.LoginUserDetailService;
import com.github.shop.infrastructure.security.login.UserRole;
import com.github.shop.infrastructure.security.token.JwtAuthTokenFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.github.shop.infrastructure.security.login.UserRole.ROLE_ADMIN;
import static com.github.shop.infrastructure.security.login.UserRole.ROLE_CUSTOMER;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthTokenFilter jwtAuthTokenFilter;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    UserDetailsService userDetailsService(LoginFacade loginFacade) {
        return new LoginUserDetailService(loginFacade);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole(ROLE_ADMIN.getRole())
                        .requestMatchers("/token/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/register/**").hasAnyRole(ROLE_CUSTOMER.getRole())
                        .requestMatchers("/login").hasAnyRole(ROLE_CUSTOMER.getRole(),ROLE_ADMIN.getRole())
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .anyRequest().permitAll())
                .formLogin(FormLoginConfigurer::permitAll)
                .headers(HeadersConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((handling) -> handling.configure((httpSecurity)))
                .addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
