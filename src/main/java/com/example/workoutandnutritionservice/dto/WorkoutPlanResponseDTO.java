package com.example.workoutandnutritionservice.dto;

import com.example.workoutandnutritionservice.enumeration.Difficulty;
import com.example.workoutandnutritionservice.enumeration.PlanStatus;
import com.example.workoutandnutritionservice.enumeration.PlanType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record WorkoutPlanResponseDTO(
        UUID id,
        @JsonProperty("weekly_split")
        String weeklySplit,
        @JsonProperty("plan_type")
        PlanType planType,
        Difficulty difficulty,
        PlanStatus status,
        List<WorkoutDayDTO> days
) {
}
