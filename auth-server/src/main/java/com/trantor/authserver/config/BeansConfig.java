package com.trantor.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * BeansConfig
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
@Configuration
public class BeansConfig {

    @Bean("encoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
