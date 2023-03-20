package com.example.mefit.services.exercise;

import com.example.mefit.services.CrudService;
import com.example.mefit.models.Exercise;
public interface ExerciseService  extends CrudService<Exercise, Integer> {
    Exercise update(int id, Exercise entity);
}
