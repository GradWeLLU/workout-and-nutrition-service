package com.example.workoutandnutritionservice.mapper;

import com.example.workoutandnutritionservice.dto.NutritionPlanDTO;
import com.example.workoutandnutritionservice.entity.NutritionPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = MealDayMapper.class)
public interface NutritionPlanMapper {

    @Mapping(target = "days", source = "mealDays")
    NutritionPlanDTO toDTO(NutritionPlan nutritionPlan);

    @Mapping(target = "mealDays", source = "days")
    NutritionPlan toEntity(NutritionPlanDTO dto);
}
