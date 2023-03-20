package com.example.mefit.models.dtos.program;

import com.example.mefit.models.Program;
import com.example.mefit.models.Workout;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class ProgramDTO {
    private int program_id;
    private String program_name;
    private String category;
    private Boolean complete_program;
    private Set<Integer> goals;
    private Set<Integer> workouts;
}