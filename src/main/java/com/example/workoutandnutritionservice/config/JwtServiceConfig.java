package com.example.workoutandnutritionservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wellu.common.security.JwtConfig;
import org.wellu.common.security.JwtService;

@Configuration
public class JwtServiceConfig {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    public JwtService jwtService() {
        JwtConfig config = JwtConfig.builder()
                .secret(jwtSecret)
                .expiration(86400000) // 1 day
                .build();

        return new JwtService(config);
    }
}
