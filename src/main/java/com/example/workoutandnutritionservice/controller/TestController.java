package com.example.workoutandnutritionservice.controller;

import com.example.workoutandnutritionservice.client.UserClient;
import com.example.workoutandnutritionservice.dto.WorkoutRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final UserClient userClient;

    @GetMapping("/user")
    public WorkoutRequestDTO testUserClient(@RequestHeader("Authorization") String jwtToken){
        return userClient.getUserById(jwtToken);
    }
}
