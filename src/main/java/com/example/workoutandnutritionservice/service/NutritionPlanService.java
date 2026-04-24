package com.example.workoutandnutritionservice.service;

import com.example.workoutandnutritionservice.dto.ApiMessageResponse;
import com.example.workoutandnutritionservice.dto.NutritionPlanResponseDTO;
import com.example.workoutandnutritionservice.entity.NutritionPlan;
import com.example.workoutandnutritionservice.enumeration.PlanStatus;
import com.example.workoutandnutritionservice.mapper.NutritionPlanResponseMapper;
import com.example.workoutandnutritionservice.repository.NutritionPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NutritionPlanService {
    private final NutritionPlanRepository nutritionPlanRepository;
    private final NutritionPlanResponseMapper nutritionPlanResponseMapper;

    public ResponseEntity<List<NutritionPlanResponseDTO>> getUserPlans(UUID userId) {
        List<NutritionPlanResponseDTO> plans = nutritionPlanRepository.findAllByUserId(userId)
                .stream()
                .map(nutritionPlanResponseMapper::toDTO)
                .toList();

        return ResponseEntity.ok(plans);
    }

    @Transactional
    public ResponseEntity<ApiMessageResponse> activatePlan(UUID userId, UUID planId) {
        NutritionPlan newPlan = nutritionPlanRepository.findById(planId)
                .orElse(null);

        if (newPlan == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiMessageResponse("Plan not found"));
        }

        if (!newPlan.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiMessageResponse("You are not allowed to activate this plan"));
        }

        nutritionPlanRepository.findByUserIdAndStatus(userId, PlanStatus.ACTIVE)
                .ifPresent(old -> {
                    if (!old.getId().equals(planId)) {
                        old.setStatus(PlanStatus.INACTIVE);
                        nutritionPlanRepository.save(old);
                    }
                });

        newPlan.setStatus(PlanStatus.ACTIVE);
        nutritionPlanRepository.save(newPlan);

        return ResponseEntity.ok(new ApiMessageResponse("Nutrition plan activated successfully"));
    }

    public ResponseEntity<NutritionPlanResponseDTO> getActivePlan(UUID userId) {
        NutritionPlan plan = nutritionPlanRepository.findByUserIdAndStatus(userId, PlanStatus.ACTIVE)
                .orElse(null);

        if (plan == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(nutritionPlanResponseMapper.toDTO(plan));
    }

    public ResponseEntity<NutritionPlanResponseDTO> getPlanById(UUID planId, UUID userId) {
        NutritionPlan plan = nutritionPlanRepository.findById(planId)
                .orElse(null);

        if (plan == null) {
            return ResponseEntity.notFound().build();
        }

        if (!plan.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(nutritionPlanResponseMapper.toDTO(plan));
    }
}
