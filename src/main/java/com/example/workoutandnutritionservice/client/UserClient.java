package com.example.workoutandnutritionservice.client;

import com.example.workoutandnutritionservice.dto.WorkoutRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "user-management-service", url = "${user.service.url}")
public interface UserClient {

    @GetMapping("/profile/me")
    WorkoutRequestDTO getUserById( @RequestHeader("Authorization") String jwtToken);
}