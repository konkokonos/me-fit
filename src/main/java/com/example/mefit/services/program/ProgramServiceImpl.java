package com.example.mefit.services.program;

import com.example.mefit.exceptions.ProgramNotFoundException;
import com.example.mefit.models.Program;
import com.example.mefit.repositories.ProgramRepository;

import com.example.mefit.exceptions.WorkoutNotFoundException;
import com.example.mefit.exceptions.ExerciseNotFoundException;
import com.example.mefit.models.Workout;
import com.example.mefit.models.Exercise;
import com.example.mefit.repositories.WorkoutRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final WorkoutRepository workoutRepository;

    public ProgramServiceImpl(ProgramRepository programRepository, WorkoutRepository workoutRepository) {
        this.programRepository = programRepository;
        this.workoutRepository = workoutRepository;
    }


    @Override
    public Program findById(Integer id) {
        return programRepository.findById(id).orElseThrow(()->new ProgramNotFoundException(id));
    }

    @Override
    public Collection<Program> findAll() {
        return programRepository.findAll();
    }

    @Override
    public Program add(Program entity) {
        return programRepository.save(entity);
    }

    @Override
    public Program update(Program entity) {
        return programRepository.save(entity);
    }

    @Override
    public Program update(int id, Program entity) {
        return programRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (programRepository.existsById(id)) {
            Program pro = programRepository.findById(id).orElseThrow(()->new ProgramNotFoundException(id));
            pro.setWorkouts(null);
            programRepository.delete(pro);
        }
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }

    @Override
    public Collection<Workout> getWorkouts(int programId) {
        return programRepository
                .findById(programId)
                .orElseThrow(()->new ProgramNotFoundException(programId))
                .getWorkouts();
    }
}
