package com.example.workoutandnutritionservice.mapper;

import com.example.workoutandnutritionservice.dto.NutritionPlanResponseDTO;
import com.example.workoutandnutritionservice.entity.NutritionPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = MealDayMapper.class)
public interface NutritionPlanResponseMapper {

    @Mapping(target = "days", source = "mealDays")
    NutritionPlanResponseDTO toDTO(NutritionPlan nutritionPlan);
}
