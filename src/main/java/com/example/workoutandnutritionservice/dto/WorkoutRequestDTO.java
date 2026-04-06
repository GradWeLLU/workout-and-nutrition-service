package com.example.workoutandnutritionservice.dto;

import java.util.List;

public record WorkoutRequestDTO(
        String goal,                        // e.g., "muscle gain", "fat loss"
        int days,                            // number of workout days
        int age,
        double weight,
        double height,
        double bmi,
        Integer sessionDuration,             // optional
        String preferredDifficultyLevel,     // "BEGINNER", "INTERMEDIATE", "ADVANCED", optional
        List<String> preferredEquipment,     // optional
        String experienceLevel,              // "beginner", "intermediate", "advanced"
        List<String> injuries                // optional
) { }