package com.example.mefit.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ExerciseNotFoundException extends EntityNotFoundException {
    public ExerciseNotFoundException(int id) {
        super("Exercise does not exist with ID: "+id);
    }
}
