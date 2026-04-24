package com.example.workoutandnutritionservice.controller;

import com.example.workoutandnutritionservice.dto.ApiMessageResponse;
import com.example.workoutandnutritionservice.dto.NutritionPlanResponseDTO;
import com.example.workoutandnutritionservice.security.JwtUserPrincipal;
import com.example.workoutandnutritionservice.service.NutritionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/nutrition")
@RequiredArgsConstructor
public class NutritionPlanController {
    private final NutritionPlanService nutritionPlanService;

    @GetMapping("/plans")
    public ResponseEntity<List<NutritionPlanResponseDTO>> getAllPlans(@AuthenticationPrincipal JwtUserPrincipal user) {
        return nutritionPlanService.getUserPlans(user.getUserId());
    }

    @PostMapping("/plans/{planId}/activate")
    public ResponseEntity<ApiMessageResponse> activatePlan(@PathVariable UUID planId,
                                                           @AuthenticationPrincipal JwtUserPrincipal user) {
        return nutritionPlanService.activatePlan(user.getUserId(), planId);
    }

    @GetMapping("/plans/active")
    public ResponseEntity<NutritionPlanResponseDTO> getActivePlan(@AuthenticationPrincipal JwtUserPrincipal user) {
        return nutritionPlanService.getActivePlan(user.getUserId());
    }

    @GetMapping("/plans/{planId}")
    public ResponseEntity<NutritionPlanResponseDTO> getPlanById(@PathVariable UUID planId,
                                                                @AuthenticationPrincipal JwtUserPrincipal user) {
        return nutritionPlanService.getPlanById(planId, user.getUserId());
    }
}
