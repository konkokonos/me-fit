package com.example.mefit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

@Entity
@Setter
@Getter
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workout_id;
    @Column(length = 30, nullable = false)
    private String workout_name;
    @Column(length = 30)
    private String type;
    @Column(nullable = false)
    private boolean complete_workout;
    @JsonIgnore
    @ManyToMany(mappedBy = "workouts")
    private Set<Program> programs;
    @JsonIgnore
    @OneToMany(mappedBy = "workout")
    private Set<Exercise> exercises;
}
