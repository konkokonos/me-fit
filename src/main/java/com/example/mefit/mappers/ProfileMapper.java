package com.example.mefit.mappers;

import com.example.mefit.models.Goal;
import com.example.mefit.models.Profile;
import com.example.mefit.models.dtos.profile.ProfileDTO;
import com.example.mefit.services.goal.GoalService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ProfileMapper {
    @Autowired
    GoalService goalService;
    @Mapping(target = "goal", source = "goal.goal_id")
    public abstract ProfileDTO profileToProfileDTO(Profile profile);

    public abstract Collection<ProfileDTO> profileToProfileDTO(Collection<Profile> profile);

    @Named("goalIdToGoal")
    Goal mapIdToGoal(int id) {
        return goalService.findById(id);
    }
    @Mapping(target = "goal", source = "goal", qualifiedByName="goalIdToGoal")
    public abstract Profile profileDTOToProfile(ProfileDTO profileDTO);
}
