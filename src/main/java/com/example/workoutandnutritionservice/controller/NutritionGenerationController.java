package com.example.workoutandnutritionservice.controller;

import com.example.workoutandnutritionservice.dto.AIResponseDTO;
import com.example.workoutandnutritionservice.dto.NutritionPlanDTO;
import com.example.workoutandnutritionservice.dto.NutritionRequestDTO;
import com.example.workoutandnutritionservice.service.NutritionGenerationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nutrition")
@RequiredArgsConstructor
public class NutritionGenerationController {
    private final NutritionGenerationService nutritionGenerationService;

    public ResponseEntity<NutritionPlanDTO> generateNutritionPlan(@RequestHeader("Authorization") String jwtToken){
        return nutritionGenerationService.generateNutrition(jwtToken);
    }
}
