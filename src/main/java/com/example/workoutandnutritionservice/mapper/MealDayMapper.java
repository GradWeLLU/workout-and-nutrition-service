package com.example.workoutandnutritionservice.mapper;

import com.example.workoutandnutritionservice.dto.MealDayDTO;
import com.example.workoutandnutritionservice.entity.MealDay;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = MealMapper.class)
public interface MealDayMapper {

    MealDayDTO toDTO(MealDay mealDay);

    MealDay toEntity(MealDayDTO dto);
}
