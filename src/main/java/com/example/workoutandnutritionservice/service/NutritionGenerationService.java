package com.example.workoutandnutritionservice.service;

import com.example.workoutandnutritionservice.client.AIClient;
import com.example.workoutandnutritionservice.client.UserClient;
import com.example.workoutandnutritionservice.dto.NutritionPlanDTO;
import com.example.workoutandnutritionservice.dto.NutritionRequestDTO;
import com.example.workoutandnutritionservice.dto.NutritionResponseDTO;
import com.example.workoutandnutritionservice.dto.UserNutritionPlanDetailsDTO;
import com.example.workoutandnutritionservice.entity.NutritionPlan;
import com.example.workoutandnutritionservice.enumeration.PlanStatus;
import com.example.workoutandnutritionservice.mapper.NutritionMapper;
import com.example.workoutandnutritionservice.mapper.NutritionPlanMapper;
import com.example.workoutandnutritionservice.repository.NutritionPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NutritionGenerationService {
    private final UserClient userClient;
    private final AIClient aIClient;
    private final NutritionPlanMapper nutritionPlanMapper;
    private final NutritionPlanRepository nutritionPlanRepository;

    public ResponseEntity<NutritionResponseDTO> generateNutrition(String jwtToken){
        UserNutritionPlanDetailsDTO userDetails = fetchUserDetails(jwtToken);
        UUID userId = userDetails.userID();
        NutritionRequestDTO nutritionRequest = NutritionMapper.toNutritionRequest(userDetails);
        NutritionResponseDTO aiReply = fetchNutritionPlan(nutritionRequest);
        NutritionPlanDTO planDTO = aiReply.nutritionPlan();
        NutritionPlan plan = nutritionPlanMapper.toEntity(planDTO);
        plan.setUserId(userId);
        plan.setStatus(PlanStatus.INACTIVE);
        nutritionPlanRepository.save(plan);

        return ResponseEntity.ok(aiReply);
    }

    public UserNutritionPlanDetailsDTO fetchUserDetails(String jwtToken){
        return userClient.getNutritionDetails(jwtToken);
    }

    public NutritionResponseDTO fetchNutritionPlan(NutritionRequestDTO nutritionRequest){
        return aIClient.generateNutrition(nutritionRequest);
    }
}
