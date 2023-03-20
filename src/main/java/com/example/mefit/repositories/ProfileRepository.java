package com.example.mefit.repositories;

import com.example.mefit.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile,Integer> {

    @Query("UPDATE Profile p\n" +
            "SET p.goal.goal_id = ?1\n" +
            "WHERE p.profile_id =?2")
    @Modifying
    int setGoal(int goalId, int profId);
}
