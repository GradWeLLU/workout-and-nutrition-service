package com.example.workoutandnutritionservice.mapper;

import com.example.workoutandnutritionservice.dto.WorkoutPlanResponseDTO;
import com.example.workoutandnutritionservice.entity.WorkoutPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = WorkoutDayMapper.class)
public interface WorkoutPlanResponseMapper {

    @Mapping(target = "days", source = "workoutDays")
    WorkoutPlanResponseDTO toDTO(WorkoutPlan workoutPlan);
}
