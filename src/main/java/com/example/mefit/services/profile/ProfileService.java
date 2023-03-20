package com.example.mefit.services.profile;

import com.example.mefit.services.CrudService;
import com.example.mefit.models.Profile;
public interface ProfileService  extends CrudService<Profile, Integer> {
    Profile update(int id, Profile entity);
}
