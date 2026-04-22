package com.example.workoutandnutritionservice.config;

import com.example.workoutandnutritionservice.security.JwtUserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.wellu.common.security.JwtAuthenticationConverter;

import java.util.List;

@Configuration
public class JwtConfigBeans {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        return userId -> {
            JwtUserPrincipal principal = new JwtUserPrincipal(userId);

            return new UsernamePasswordAuthenticationToken(
                    principal,
                    null,
                    List.of()
            );
        };
    }
}
