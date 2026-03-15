package com.example.workoutandnutritionservice.entity;

import com.example.workoutandnutritionservice.enumeration.ExerciseDifficulty;
import com.example.workoutandnutritionservice.enumeration.ExerciseType;
import com.example.workoutandnutritionservice.enumeration.MuscleGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    // Multi-muscle support
    @ElementCollection(targetClass = MuscleGroup.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "exercise_muscle_groups",
            joinColumns = @JoinColumn(name = "exercise_id")
    )
    @Column(name = "muscle_group", nullable = false)
    private Set<MuscleGroup> muscleGroups = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExerciseType type;

    private int sets;

    private int reps;

    @Enumerated(EnumType.STRING)
    private ExerciseDifficulty difficulty;

    @Column(name = "video_url")
    private String videoURL;

    @Column(name = "rest_time")
    private double restTime;
}