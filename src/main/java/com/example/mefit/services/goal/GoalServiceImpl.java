package com.example.mefit.services.goal;

import com.example.mefit.exceptions.GoalNotFoundException;
import com.example.mefit.exceptions.ProfileNotFoundException;
import com.example.mefit.models.Goal;
import com.example.mefit.models.Profile;
import com.example.mefit.models.Program;
import com.example.mefit.repositories.GoalRepository;
import com.example.mefit.repositories.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final ProfileRepository profileRepository;

    public GoalServiceImpl(GoalRepository goalRepository, ProfileRepository profileRepository) {
        this.goalRepository = goalRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Goal findById(Integer id) {
        return goalRepository.findById(id).orElseThrow(()->new GoalNotFoundException(id));
    }

    @Override
    public Collection<Goal> findAll() {
        return goalRepository.findAll();
    }

    @Override
    public Goal add(Goal entity) {
        return goalRepository.save(entity);
    }

    @Override
    public Goal update(Goal entity) {
        return goalRepository.save(entity);
    }

    @Override
    public Goal update(int id, Goal entity) {
        return goalRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (goalRepository.existsById(id)) {
            Goal go = goalRepository.findById(id).orElseThrow(()->new GoalNotFoundException(id));
            go.setProfiles(null);
            goalRepository.delete(go);
        }
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }

    @Override
    public Collection<Program> getPrograms(int goalId) {
        return goalRepository
                .findById(goalId)
                .orElseThrow(()->new GoalNotFoundException(goalId))
                .getPrograms();
    }
}
