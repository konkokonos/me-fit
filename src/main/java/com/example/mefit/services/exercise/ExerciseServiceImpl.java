package com.example.mefit.services.exercise;

import com.example.mefit.exceptions.ExerciseNotFoundException;
import com.example.mefit.models.Exercise;
import com.example.mefit.repositories.ExerciseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExerciseServiceImpl implements ExerciseService{

    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise findById(Integer id) {
        return exerciseRepository.findById(id).orElseThrow(()->new ExerciseNotFoundException(id));
    }

    @Override
    public Collection<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise add(Exercise entity) {
        return exerciseRepository.save(entity);
    }

    @Override
    public Exercise update(Exercise entity) {
        return null;
    }

    @Override
    public Exercise update(int id, Exercise entity) {
        return exerciseRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (exerciseRepository.existsById(id)) {
            Exercise prof = exerciseRepository.findById(id).orElseThrow(()->new ExerciseNotFoundException(id));
            exerciseRepository.delete(prof);
        }
    }
    @Override
    public boolean exists(Integer integer) {
        return false;
    }
}
