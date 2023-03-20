package com.example.mefit.services.workout;

import com.example.mefit.models.Workout;
import com.example.mefit.models.Exercise;
import com.example.mefit.services.CrudService;

import java.util.Collection;

public interface WorkoutService extends CrudService<Workout, Integer> {
    Workout update(int id, Workout entity);

    Collection<Exercise> getExercises(int workoutId);
    void updateExercises(int workoutId, int[] exercisesIds);
}
