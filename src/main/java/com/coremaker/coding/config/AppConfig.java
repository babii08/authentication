package com.coremaker.coding.config;

import com.coremaker.coding.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepo userRepo;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            final var user = userRepo.findByEmail(username);
            final var authorities = List.of(new SimpleGrantedAuthority("USER"));
            return new User(user.getEmail(), user.getPassword(), authorities);
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
