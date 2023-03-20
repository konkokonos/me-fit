package com.example.mefit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profile_id;
    @Column()
    private Integer age;
    @Column()
    private Integer weight;
    @Column()
    private Integer height;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="goal_id")
    private Goal goal;
}
