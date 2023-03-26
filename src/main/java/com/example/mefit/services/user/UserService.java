package com.example.mefit.services.user;

import com.example.mefit.models.AppUser;

import java.util.Collection;

public interface UserService {
    AppUser findById(String uid);
    Collection<AppUser> findAll();
    AppUser add(String uid);
    AppUser add(AppUser user);
    void update(AppUser user);
    void delete(String uid);

}
