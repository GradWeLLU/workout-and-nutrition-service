package com.example.workoutandnutritionservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String mealType;

    @Positive
    private int totalCalories;

    @Column(columnDefinition = "jsonb")
    private String totalMacros;

    @Column(columnDefinition = "jsonb")
    private String totalMicros;

    @ManyToOne
    @JoinColumn(name = "nutrition_plan_id")
    private NutritionPlan nutritionPlan;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private List<FoodItem> foodItems;


}
