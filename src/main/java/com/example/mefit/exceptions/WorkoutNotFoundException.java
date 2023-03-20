package com.example.mefit.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class WorkoutNotFoundException extends EntityNotFoundException {
    public WorkoutNotFoundException(int id) {
        super("Workout does not exist with ID: "+id);
    }
}
