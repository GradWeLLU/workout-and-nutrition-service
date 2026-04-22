package com.example.workoutandnutritionservice.mapper;

import com.example.workoutandnutritionservice.dto.WorkoutPlanDTO;
import com.example.workoutandnutritionservice.entity.WorkoutPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = WorkoutDayMapper.class) // Delegate mapping of nested WorkoutDay -> WorkoutDayDTO
public interface WorkoutPlanMapper {

    WorkoutPlanMapper INSTANCE = Mappers.getMapper(WorkoutPlanMapper.class);

    // Entity -> DTO
    @Mapping(target = "days", source = "workoutDays")
    WorkoutPlanDTO toDTO(WorkoutPlan workoutPlan);

    // DTO -> Entity
    @Mapping(target = "workoutDays", source = "days")
    WorkoutPlan toEntity(WorkoutPlanDTO dto);

}
