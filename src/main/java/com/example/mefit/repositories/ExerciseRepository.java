package com.example.mefit.repositories;

import com.example.mefit.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ExerciseRepository extends JpaRepository<Exercise,Integer> {

    @Query("UPDATE Exercise ex\n" +
            "SET ex.workout.workout_id = ?1\n" +
            "WHERE ex.exercise_id =?2")
    @Modifying
    int setWorkout(int workoutId, int exerId);
}