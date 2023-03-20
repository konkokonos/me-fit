package com.example.mefit.models.dtos.exercise;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDTO {
    private Integer exercise_id;
    private String exercise_name;
    private String description;
    private String target_muscle_group;
    private Integer repetitions;
    private String image;
    private String video;
    private Boolean complete_exercise;
    private Integer workout;
}
