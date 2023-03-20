package com.example.mefit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goal_id;
    @Column(length = 30, nullable = false)
    private String goal_name;
    @Column
    private LocalDate start_date;
    @Column
    private LocalDate end_date;
    @Column()
    private Integer total_programs;
    @Column()
    private Integer completed_programs;
    @Column(nullable = false)
    private boolean complete_goal;
    @OneToMany(mappedBy = "goal")
    private Set<Profile> profiles;

    @ManyToMany
    @JoinTable(
            name = "goal_program",
            joinColumns = @JoinColumn(name = "goal_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private Set<Program> programs;
}
