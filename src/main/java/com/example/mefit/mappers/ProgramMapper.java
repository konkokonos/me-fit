package com.example.mefit.mappers;

import com.example.mefit.models.Goal;
import com.example.mefit.models.Program;
import com.example.mefit.models.Workout;
import com.example.mefit.models.dtos.program.ProgramDTO;
import com.example.mefit.services.goal.GoalService;
import com.example.mefit.services.workout.WorkoutService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProgramMapper {
    @Autowired
    protected WorkoutService workoutService;
    @Autowired
    protected GoalService goalService;

    @Mapping(target = "workouts", source = "workouts", qualifiedByName ="workoutToWorkoutId")
    @Mapping(target = "goals", source = "goals", qualifiedByName ="goalToGoalId")
    public abstract ProgramDTO programToProgramDTO(Program program);
    public abstract Collection<ProgramDTO> programToProgramDTO(Collection<Program> program);
    @Named(value = "workoutToWorkoutId")
    Set<Integer> mapWorkout(Set<Workout> value) {
        if (value == null)
            return null;
        return value.stream()
                .map(s -> s.getWorkout_id())
                .collect(Collectors.toSet());
    }

    @Named(value = "goalToGoalId")
    Set<Integer> mapGoal(Set<Goal> value) {
        if (value == null)
            return null;
        return value.stream()
                .map(s -> s.getGoal_id())
                .collect(Collectors.toSet());
    }

    @Named("workoutIdsToWorkout")
    Set<Workout> mapIdsToWorkout(Set<Integer> id) {
        return id.stream()
                .map( i-> workoutService.findById(i))
                .collect(Collectors.toSet());
    }


    @Named("goalIdsToGoal")
    Set<Goal> mapIdsToGoal(Set<Integer> id) {
        return id.stream()
                .map( i-> goalService.findById(i))
                .collect(Collectors.toSet());
    }
    @Mapping(target = "workouts", source = "workouts", qualifiedByName = "workoutIdsToWorkout")
    @Mapping(target = "goals", source = "goals", qualifiedByName = "goalIdsToGoal")
    public abstract Program programDTOToProgram(ProgramDTO programDTO);
}