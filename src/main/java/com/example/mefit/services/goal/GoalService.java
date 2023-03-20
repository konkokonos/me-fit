package com.example.mefit.services.goal;

import com.example.mefit.models.Program;
import com.example.mefit.models.Goal;
import com.example.mefit.models.Workout;
import com.example.mefit.services.CrudService;

import java.util.Collection;

public interface GoalService extends CrudService<Goal, Integer> {
    Goal update(int id, Goal entity);
    Collection<Program> getPrograms(int goalId);
}
