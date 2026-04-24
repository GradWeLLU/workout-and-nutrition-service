package com.example.workoutandnutritionservice.repository;

import com.example.workoutandnutritionservice.entity.NutritionPlan;
import com.example.workoutandnutritionservice.enumeration.PlanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NutritionPlanRepository extends JpaRepository<NutritionPlan, UUID> {
    List<NutritionPlan> findAllByUserId(UUID userId);

    Optional<NutritionPlan> findByUserIdAndStatus(UUID userId, PlanStatus status);
}
