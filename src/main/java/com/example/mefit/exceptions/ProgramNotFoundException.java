package com.example.mefit.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ProgramNotFoundException extends EntityNotFoundException {
    public ProgramNotFoundException(int id) {
        super("Program does not exist with ID: "+id);
    }
}
