package com.example.workoutandnutritionservice.mapper;

import com.example.workoutandnutritionservice.dto.MealDTO;
import com.example.workoutandnutritionservice.entity.Meal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MealMapper {

    MealDTO toDTO(Meal meal);

    Meal toEntity(MealDTO dto);
}
