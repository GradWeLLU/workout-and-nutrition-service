package com.example.workoutandnutritionservice.dto;

import com.example.workoutandnutritionservice.enumeration.PlanStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record NutritionPlanResponseDTO(
        UUID id,
        String goal,
        @JsonProperty("daily_calories")
        int dailyCalories,
        PlanStatus status,
        List<MealDayDTO> days
) {
}
