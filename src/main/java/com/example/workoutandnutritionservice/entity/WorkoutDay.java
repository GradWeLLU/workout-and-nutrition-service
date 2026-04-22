package com.example.workoutandnutritionservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workout_days")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDay {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String day;


    @Column(nullable = false)
    private String focus;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "workout_day_exercises",
            joinColumns = @JoinColumn(name = "workout_day_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise> exercises;

}
