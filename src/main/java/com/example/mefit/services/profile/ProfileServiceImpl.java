package com.example.mefit.services.profile;

import com.example.mefit.exceptions.GoalNotFoundException;
import com.example.mefit.exceptions.ProfileNotFoundException;
import com.example.mefit.models.Profile;
import com.example.mefit.repositories.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProfileServiceImpl implements ProfileService{

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile findById(Integer id) {
        return profileRepository.findById(id).orElseThrow(()->new ProfileNotFoundException(id));
    }

    @Override
    public Collection<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile add(Profile entity) {
        return profileRepository.save(entity);
    }

    @Override
    public Profile update(Profile entity) {
        return null;
    }

    @Override
    public Profile update(int id, Profile entity) {
        return profileRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (profileRepository.existsById(id)) {
            Profile prof = profileRepository.findById(id).orElseThrow(()->new ProfileNotFoundException(id));
            profileRepository.delete(prof);
        }
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }
}
