package com.example.mefit.models.dtos.workout;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
public class WorkoutDTO {
    private Integer workout_id;
    private String workout_name;
    private String type;
    private Boolean complete_workout;
    private Set<Integer> programs;
    private Set<Integer> exercises;


}