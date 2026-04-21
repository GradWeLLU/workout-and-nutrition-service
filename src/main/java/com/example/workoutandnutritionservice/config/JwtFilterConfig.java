package com.example.workoutandnutritionservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wellu.common.security.JwtAuthFilter;
import org.wellu.common.security.JwtAuthenticationConverter;
import org.wellu.common.security.JwtService;

@Configuration
public class JwtFilterConfig {

    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtService jwtService,
                                       JwtAuthenticationConverter converter) {
        return new JwtAuthFilter(jwtService, converter);
    }
}