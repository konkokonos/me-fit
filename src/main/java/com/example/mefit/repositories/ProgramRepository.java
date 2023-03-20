package com.example.mefit.repositories;

import com.example.mefit.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program,Integer> {
}
