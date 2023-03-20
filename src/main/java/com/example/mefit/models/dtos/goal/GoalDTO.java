package com.example.mefit.models.dtos.goal;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
public class GoalDTO {
    private Integer goal_id;
    private String goal_name;
    private Date start_date;
    private Date end_date;
    private Integer total_programs;
    private Integer completed_programs;
    private Boolean complete_goal;
    private Set<Integer> profiles;
    private Set<Integer> programs;

}
