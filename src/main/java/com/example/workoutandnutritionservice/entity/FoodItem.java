package com.example.workoutandnutritionservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "food_items")
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String foodName;

    private String foodCategory;

    @Positive
    private int calories;

    @Column(columnDefinition = "jsonb")
    private String macros;

    @Column(columnDefinition = "jsonb")
    private String micros;

    @ManyToOne
    @JoinColumn(name = "id")
    private Meal meal;


}
