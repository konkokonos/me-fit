package com.example.mefit.services.program;

import com.example.mefit.models.Program;
import com.example.mefit.models.Workout;
import com.example.mefit.services.CrudService;

import java.util.Collection;

public interface ProgramService extends CrudService<Program, Integer> {
    Program update(int id, Program entity);

    Collection<Workout> getWorkouts(int programId);
}
