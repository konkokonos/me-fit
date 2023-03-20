package com.example.mefit.mappers;

import com.example.mefit.models.Program;
import com.example.mefit.models.Workout;
import com.example.mefit.models.Exercise;
import com.example.mefit.models.dtos.workout.WorkoutDTO;
import com.example.mefit.services.exercise.ExerciseService;
import com.example.mefit.services.program.ProgramService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class WorkoutMapper {
    @Autowired
    protected ExerciseService exerciseService;
    @Autowired
    protected ProgramService programService;

    @Mapping(target = "exercises", source = "exercises", qualifiedByName = "exerciseToExerciseId")
    @Mapping(target = "programs", source = "programs", qualifiedByName = "programToProgramId")
    public abstract WorkoutDTO workoutToWorkoutDTO(Workout workout);

    public abstract Collection<WorkoutDTO> workoutToWorkoutDTO(Collection<Workout> workout);

    @Named(value = "exerciseToExerciseId")
    Set<Integer> mapExercise(Set<Exercise> value){
        if(value == null)
            return null;
        return value.stream()
                .map(s -> s.getExercise_id())
                .collect(Collectors.toSet());
    }

    @Named("exerciseIdsToExercise")
    Set<Exercise> mapIdsToExercise(Set<Integer> id) {
        return id.stream()
                .map( i-> exerciseService.findById(i))
                .collect(Collectors.toSet());
    }
    @Named(value = "programToProgramId")
    Set<Integer> mapProgram(Set<Program> value){
        if(value == null)
            return null;
        return value.stream()
                .map(s -> s.getProgram_id())
                .collect(Collectors.toSet());
    }

    @Named("programIdsToProgram")
    Set<Program> mapIdsToProgram(Set<Integer> id) {
        return id.stream()
                .map( i-> programService.findById(i))
                .collect(Collectors.toSet());
    }

    @Mapping(target = "exercises", source = "exercises", qualifiedByName = "exerciseIdsToExercise")
    @Mapping(target = "programs", source = "programs", qualifiedByName = "programIdsToProgram")
    public abstract Workout workoutDTOToWorkout(WorkoutDTO workoutDTO);

}