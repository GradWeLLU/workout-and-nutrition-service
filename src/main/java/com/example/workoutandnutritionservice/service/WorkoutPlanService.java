package com.example.workoutandnutritionservice.service;

import com.example.workoutandnutritionservice.dto.ApiMessageResponse;
import com.example.workoutandnutritionservice.dto.WorkoutPlanResponseDTO;
import com.example.workoutandnutritionservice.entity.WorkoutPlan;
import com.example.workoutandnutritionservice.enumeration.PlanStatus;
import com.example.workoutandnutritionservice.mapper.WorkoutPlanResponseMapper;
import com.example.workoutandnutritionservice.repository.WorkoutPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutPlanService {
    private final WorkoutPlanRepository planRepository;
    private final WorkoutPlanResponseMapper workoutPlanResponseMapper;

    public ResponseEntity<List<WorkoutPlanResponseDTO>> getUserPlans(UUID userId) {
        List<WorkoutPlanResponseDTO> plans = planRepository.findAllByUserId(userId)
                .stream()
                .map(workoutPlanResponseMapper::toDTO)
                .toList();

        return ResponseEntity.ok(plans);
    }

    @Transactional
    public ResponseEntity<ApiMessageResponse> activatePlan(UUID userId, UUID planId) {
        WorkoutPlan newPlan = planRepository.findById(planId)
                .orElse(null);

        if (newPlan == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiMessageResponse("Plan not found"));
        }

        if (!newPlan.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiMessageResponse("You are not allowed to activate this plan"));
        }

        planRepository.findByUserIdAndStatus(userId, PlanStatus.ACTIVE)
                .ifPresent(old -> {
                    if (!old.getId().equals(planId)) {
                        old.setStatus(PlanStatus.INACTIVE);
                        planRepository.save(old);
                    }
                });

        newPlan.setStatus(PlanStatus.ACTIVE);
        planRepository.save(newPlan);

        return ResponseEntity.ok(new ApiMessageResponse("Workout plan activated successfully"));
    }

    public ResponseEntity<WorkoutPlanResponseDTO> getActivePlan(UUID userId) {
        WorkoutPlan plan = planRepository.findByUserIdAndStatus(userId, PlanStatus.ACTIVE)
                .orElse(null);

        if (plan == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(workoutPlanResponseMapper.toDTO(plan));
    }

    public ResponseEntity<WorkoutPlanResponseDTO> getPlanById(UUID planId, UUID userId) {
        WorkoutPlan plan = planRepository.findById(planId)
                .orElse(null);

        if (plan == null) {
            return ResponseEntity.notFound().build();
        }

        if (!plan.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(workoutPlanResponseMapper.toDTO(plan));
    }
}
