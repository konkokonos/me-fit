package com.example.mefit.mappers;

import com.example.mefit.models.Workout;
import com.example.mefit.models.dtos.workout.WorkoutDTO;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-22T17:30:43+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class WorkoutMapperImpl extends WorkoutMapper {

    @Override
    public WorkoutDTO workoutToWorkoutDTO(Workout workout) {
        if ( workout == null ) {
            return null;
        }

        WorkoutDTO workoutDTO = new WorkoutDTO();

        workoutDTO.setExercises( mapExercise( workout.getExercises() ) );
        workoutDTO.setPrograms( mapProgram( workout.getPrograms() ) );
        workoutDTO.setWorkout_id( workout.getWorkout_id() );
        workoutDTO.setWorkout_name( workout.getWorkout_name() );
        workoutDTO.setType( workout.getType() );
        workoutDTO.setComplete_workout( workout.isComplete_workout() );

        return workoutDTO;
    }

    @Override
    public Collection<WorkoutDTO> workoutToWorkoutDTO(Collection<Workout> workout) {
        if ( workout == null ) {
            return null;
        }

        Collection<WorkoutDTO> collection = new ArrayList<WorkoutDTO>( workout.size() );
        for ( Workout workout1 : workout ) {
            collection.add( workoutToWorkoutDTO( workout1 ) );
        }

        return collection;
    }

    @Override
    public Workout workoutDTOToWorkout(WorkoutDTO workoutDTO) {
        if ( workoutDTO == null ) {
            return null;
        }

        Workout workout = new Workout();

        workout.setExercises( mapIdsToExercise( workoutDTO.getExercises() ) );
        workout.setPrograms( mapIdsToProgram( workoutDTO.getPrograms() ) );
        workout.setWorkout_id( workoutDTO.getWorkout_id() );
        workout.setWorkout_name( workoutDTO.getWorkout_name() );
        workout.setType( workoutDTO.getType() );
        if ( workoutDTO.getComplete_workout() != null ) {
            workout.setComplete_workout( workoutDTO.getComplete_workout() );
        }

        return workout;
    }
}
