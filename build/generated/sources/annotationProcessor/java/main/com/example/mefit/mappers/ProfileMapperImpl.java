package com.example.mefit.mappers;

import com.example.mefit.models.Goal;
import com.example.mefit.models.Profile;
import com.example.mefit.models.dtos.profile.ProfileDTO;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-22T17:30:43+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class ProfileMapperImpl extends ProfileMapper {

    @Override
    public ProfileDTO profileToProfileDTO(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setGoal( profileGoalGoal_id( profile ) );
        profileDTO.setProfile_id( profile.getProfile_id() );
        profileDTO.setAge( profile.getAge() );
        profileDTO.setWeight( profile.getWeight() );
        profileDTO.setHeight( profile.getHeight() );

        return profileDTO;
    }

    @Override
    public Collection<ProfileDTO> profileToProfileDTO(Collection<Profile> profile) {
        if ( profile == null ) {
            return null;
        }

        Collection<ProfileDTO> collection = new ArrayList<ProfileDTO>( profile.size() );
        for ( Profile profile1 : profile ) {
            collection.add( profileToProfileDTO( profile1 ) );
        }

        return collection;
    }

    @Override
    public Profile profileDTOToProfile(ProfileDTO profileDTO) {
        if ( profileDTO == null ) {
            return null;
        }

        Profile profile = new Profile();

        if ( profileDTO.getGoal() != null ) {
            profile.setGoal( mapIdToGoal( profileDTO.getGoal().intValue() ) );
        }
        profile.setProfile_id( profileDTO.getProfile_id() );
        profile.setAge( profileDTO.getAge() );
        profile.setWeight( profileDTO.getWeight() );
        profile.setHeight( profileDTO.getHeight() );

        return profile;
    }

    private Integer profileGoalGoal_id(Profile profile) {
        if ( profile == null ) {
            return null;
        }
        Goal goal = profile.getGoal();
        if ( goal == null ) {
            return null;
        }
        Integer goal_id = goal.getGoal_id();
        if ( goal_id == null ) {
            return null;
        }
        return goal_id;
    }
}
