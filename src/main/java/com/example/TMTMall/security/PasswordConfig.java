package com.example.TMTMall.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Using BCryptPasswordEncoder with strength of 10 (you can adjust the strength as needed)
        return new BCryptPasswordEncoder(10);
    }
}
