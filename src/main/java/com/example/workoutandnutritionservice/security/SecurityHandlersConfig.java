package com.example.workoutandnutritionservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wellu.common.security.CustomAccessDeniedHandler;
import org.wellu.common.security.CustomAuthenticationEntryPoint;

@Configuration
public class SecurityHandlersConfig {

    @Bean
    public CustomAuthenticationEntryPoint authenticationEntryPoint(ObjectMapper objectMapper) {
        return new CustomAuthenticationEntryPoint(objectMapper);
    }

    @Bean
    public CustomAccessDeniedHandler accessDeniedHandler(ObjectMapper objectMapper) {
        return new CustomAccessDeniedHandler(objectMapper);
    }
}