package com.example.workoutandnutritionservice.entity;

import com.example.workoutandnutritionservice.enumeration.PlanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "nutrition_plans")
public class NutritionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    private String goal;

    @Column(name = "daily_calories")
    private int dailyCalories;

    @Enumerated(EnumType.STRING)
    private PlanStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "nutrition_plan_id")
    private List<MealDay> mealDays;

}
