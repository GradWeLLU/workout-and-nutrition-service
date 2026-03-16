package com.example.workoutandnutritionservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
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

    private Date startDate;

    private Date endDate;

    @OneToMany(mappedBy = "nutritionPlan", cascade = CascadeType.ALL)
    private List<Meal> meals;

}
