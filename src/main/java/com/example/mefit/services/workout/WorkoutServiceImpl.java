package com.example.mefit.services.workout;

import com.example.mefit.exceptions.WorkoutNotFoundException;
import com.example.mefit.exceptions.ExerciseNotFoundException;
import com.example.mefit.models.Workout;
import com.example.mefit.models.Exercise;
import com.example.mefit.repositories.WorkoutRepository;
import com.example.mefit.repositories.ExerciseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {

        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Workout findById(Integer id) {
        return workoutRepository.findById(id).orElseThrow(()->new WorkoutNotFoundException(id));
    }

    @Override
    public Collection<Workout> findAll() {
        return workoutRepository.findAll();
    }

    @Override
    public Workout add(Workout entity) {
        return workoutRepository.save(entity);
    }

    @Override
    public Workout update(Workout entity) {
        return workoutRepository.save(entity);
    }

    @Override
    public Workout update(int id, Workout entity) {
        return workoutRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (workoutRepository.existsById(id)) {
            Workout wor = workoutRepository.findById(id).orElseThrow(()->new WorkoutNotFoundException(id));
            wor.setExercises(null);
            workoutRepository.delete(wor);
        }
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }

    @Override
    public Collection<Exercise> getExercises(int workoutId) {
        return workoutRepository
                .findById(workoutId)
                .orElseThrow(()->new WorkoutNotFoundException(workoutId))
                .getExercises();
    }

    @Override
    @Transactional
    public void updateExercises(int workoutId, int[] exercisesIds) {
        Workout wor = workoutRepository
                .findById(workoutId)
                .orElseThrow(()->new WorkoutNotFoundException(workoutId));
        Set<Exercise> exerciseSet = new HashSet<>();
        for (int id : exercisesIds) {
            exerciseRepository.setWorkout(workoutId,id);
            exerciseSet.add(exerciseRepository
                    .findById(id)
                    .orElseThrow(()->new ExerciseNotFoundException(id)));
        }
        wor.setExercises(exerciseSet);
        workoutRepository.save(wor);
    }
}
