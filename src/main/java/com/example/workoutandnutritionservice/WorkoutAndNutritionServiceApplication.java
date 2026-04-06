package com.example.workoutandnutritionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WorkoutAndNutritionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkoutAndNutritionServiceApplication.class, args);
    }

}
