package com.example.mefit.mappers;

import com.example.mefit.models.Goal;
import com.example.mefit.models.Profile;
import com.example.mefit.models.Program;
import com.example.mefit.models.dtos.goal.GoalDTO;
import com.example.mefit.services.profile.ProfileService;
import com.example.mefit.services.program.ProgramService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GoalMapper {

    @Autowired
    protected ProfileService profileService;
    @Autowired
    protected ProgramService programService;

    @Mapping(target = "profiles",source = "profiles", qualifiedByName = "profileToProfileId")
    @Mapping(target = "programs", source = "programs", qualifiedByName ="programToProgramId")
    public abstract GoalDTO goalToGoalDTO(Goal goal);

    public abstract Collection<GoalDTO> goalToGoalDTO(Collection<Goal> goal);

    @Named(value = "profileToProfileId")
    Set<Integer> mapProfile(Set<Profile> value){
        if(value == null)
            return null;
        return value.stream()
                .map(s -> s.getProfile_id())
                .collect(Collectors.toSet());
    }

    @Named("profileIdsToProfile")
    Set<Profile> mapIdsToProfile(Set<Integer> id) {
        return id.stream()
                .map( i-> profileService.findById(i))
                .collect(Collectors.toSet());
    }

    @Named(value = "programToProgramId")
    Set<Integer> mapProgram(Set<Program> value){
        if(value == null)
            return null;
        return value.stream()
                .map(s -> s.getProgram_id())
                .collect(Collectors.toSet());
    }

    @Named("programIdsToProgram")
    Set<Program> mapIdsToProgram(Set<Integer> id) {
        return id.stream()
                .map( i-> programService.findById(i))
                .collect(Collectors.toSet());
    }

    @Mapping(target = "profiles", source = "profiles", qualifiedByName = "profileIdsToProfile")
    @Mapping(target = "programs", source = "programs", qualifiedByName = "programIdsToProgram")
    public abstract Goal goalDTOToGoal(GoalDTO goalDTO);

}