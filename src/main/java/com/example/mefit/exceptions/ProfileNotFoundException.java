package com.example.mefit.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ProfileNotFoundException extends EntityNotFoundException {
    public ProfileNotFoundException(int id) {
        super("Profile does not exist with ID: "+id);
    }
}
