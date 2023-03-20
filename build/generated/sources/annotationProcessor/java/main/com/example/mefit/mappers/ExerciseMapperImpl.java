package com.example.mefit.mappers;

import com.example.mefit.models.Exercise;
import com.example.mefit.models.Workout;
import com.example.mefit.models.dtos.exercise.ExerciseDTO;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-20T11:10:44+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class ExerciseMapperImpl extends ExerciseMapper {

    @Override
    public ExerciseDTO exerciseToExerciseDTO(Exercise exercise) {
        if ( exercise == null ) {
            return null;
        }

        ExerciseDTO exerciseDTO = new ExerciseDTO();

        exerciseDTO.setWorkout( exerciseWorkoutWorkout_id( exercise ) );
        exerciseDTO.setExercise_id( exercise.getExercise_id() );
        exerciseDTO.setExercise_name( exercise.getExercise_name() );
        exerciseDTO.setDescription( exercise.getDescription() );
        exerciseDTO.setTarget_muscle_group( exercise.getTarget_muscle_group() );
        exerciseDTO.setRepetitions( exercise.getRepetitions() );
        exerciseDTO.setImage( exercise.getImage() );
        exerciseDTO.setVideo( exercise.getVideo() );
        exerciseDTO.setComplete_exercise( exercise.getComplete_exercise() );

        return exerciseDTO;
    }

    @Override
    public Collection<ExerciseDTO> exerciseToExerciseDTO(Collection<Exercise> exercise) {
        if ( exercise == null ) {
            return null;
        }

        Collection<ExerciseDTO> collection = new ArrayList<ExerciseDTO>( exercise.size() );
        for ( Exercise exercise1 : exercise ) {
            collection.add( exerciseToExerciseDTO( exercise1 ) );
        }

        return collection;
    }

    @Override
    public Exercise exerciseDTOToExercise(ExerciseDTO exerciseDTO) {
        if ( exerciseDTO == null ) {
            return null;
        }

        Exercise exercise = new Exercise();

        if ( exerciseDTO.getWorkout() != null ) {
            exercise.setWorkout( mapIdToWorkout( exerciseDTO.getWorkout().intValue() ) );
        }
        exercise.setExercise_id( exerciseDTO.getExercise_id() );
        exercise.setExercise_name( exerciseDTO.getExercise_name() );
        exercise.setDescription( exerciseDTO.getDescription() );
        exercise.setTarget_muscle_group( exerciseDTO.getTarget_muscle_group() );
        exercise.setRepetitions( exerciseDTO.getRepetitions() );
        exercise.setImage( exerciseDTO.getImage() );
        exercise.setVideo( exerciseDTO.getVideo() );
        exercise.setComplete_exercise( exerciseDTO.getComplete_exercise() );

        return exercise;
    }

    private Integer exerciseWorkoutWorkout_id(Exercise exercise) {
        if ( exercise == null ) {
            return null;
        }
        Workout workout = exercise.getWorkout();
        if ( workout == null ) {
            return null;
        }
        Integer workout_id = workout.getWorkout_id();
        if ( workout_id == null ) {
            return null;
        }
        return workout_id;
    }
}
