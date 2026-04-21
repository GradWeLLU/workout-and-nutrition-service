package com.example.workoutandnutritionservice.security;

import java.util.UUID;

public class JwtUserPrincipal {

    private final UUID userId;

    public JwtUserPrincipal(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}