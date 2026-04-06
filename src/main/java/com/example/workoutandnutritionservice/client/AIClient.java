package com.example.workoutandnutritionservice.client;

import com.example.workoutandnutritionservice.dto.WorkoutRequestDTO;
import com.example.workoutandnutritionservice.entity.WorkoutPlan;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AIClient {

    private final WebClient webClient;

    public AIClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8000").build();
    }

    public Mono<WorkoutPlan> generateWorkout(WorkoutRequestDTO requestDTO) {
        return webClient.post()
                .uri("/generate-workout")
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(WorkoutPlan.class);
    }
}