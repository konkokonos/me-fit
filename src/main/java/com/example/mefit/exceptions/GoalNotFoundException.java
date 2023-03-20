package com.example.mefit.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class GoalNotFoundException extends EntityNotFoundException {
    public GoalNotFoundException(int id) {
        super("Goal does not exist with ID: "+id);
    }
}
