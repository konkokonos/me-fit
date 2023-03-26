package com.example.mefit.services.user;

import com.example.mefit.exceptions.UserAlreadyExistsException;
import com.example.mefit.exceptions.UserNotFoundException;
import com.example.mefit.models.AppUser;
import com.example.mefit.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppUser findById(String uid) {
        return userRepository.findById(uid)
                .orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public Collection<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser add(String uid) {
        // Prevents internal server error for duplicates
        if(userRepository.existsById(uid))
            throw new UserAlreadyExistsException();
        // Create new user
        AppUser user = new AppUser();
        user.setUid(uid);
        user.setComplete(false);
        return userRepository.save(user);
    }

    @Override
    public AppUser add(AppUser user) {
        if(userRepository.existsById(user.getUid()))
            throw new UserAlreadyExistsException();
        return userRepository.save(user);
    }

    @Override
    public void update(AppUser user) {
        userRepository.save(user);
    }

    @Override
    public void delete(String uid) {
        userRepository.deleteById(uid);
    }
}
