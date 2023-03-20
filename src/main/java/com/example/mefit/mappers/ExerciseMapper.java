package com.example.mefit.mappers;

import com.example.mefit.models.Workout;
import com.example.mefit.models.Exercise;
import com.example.mefit.models.dtos.exercise.ExerciseDTO;
import com.example.mefit.services.workout.WorkoutService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ExerciseMapper {
    @Autowired
    WorkoutService workoutService;
    @Mapping(target = "workout", source = "workout.workout_id")
    public abstract ExerciseDTO exerciseToExerciseDTO(Exercise exercise);

    public abstract Collection<ExerciseDTO> exerciseToExerciseDTO(Collection<Exercise> exercise);

    @Named("workoutIdToWorkout")
    Workout mapIdToWorkout(int id) {
        return workoutService.findById(id);
    }
    @Mapping(target = "workout", source = "workout", qualifiedByName="workoutIdToWorkout")
    public abstract Exercise exerciseDTOToExercise(ExerciseDTO exerciseDTO);
}
