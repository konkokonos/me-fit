package com.example.mefit.models.dtos.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {
    private Integer profile_id;
    private Integer age;
    private Integer weight;
    private Integer height;
    private Integer goal;
}
