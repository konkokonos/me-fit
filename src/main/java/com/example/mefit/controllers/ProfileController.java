package com.example.mefit.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.example.mefit.mappers.ProfileMapper;
import com.example.mefit.models.Profile;
import com.example.mefit.models.dtos.profile.ProfileDTO;
import com.example.mefit.services.profile.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "/api/v1/profiles")
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileMapper profileMapper;


    public ProfileController(ProfileService profileService, ProfileMapper profileMapper) {
        this.profileService = profileService;
        this.profileMapper = profileMapper;
    }
    @Operation(summary = "Get all Profiles")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProfileDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "No Profiles to see",
                    content = @Content)
    })
    public ResponseEntity findall() {
        return ResponseEntity.ok(profileMapper.profileToProfileDTO(profileService.findAll()));
    }

    @Operation(summary = "Get specific Profile")
    @GetMapping("{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfileDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Profile does not exist with supplied ID",
                    content = @Content)
    })
    public ResponseEntity findByid(@PathVariable int id){
        return ResponseEntity.ok(profileMapper.profileToProfileDTO(profileService.findById(id)));
    }

    @Operation(summary = "Add a new Profile")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = @Content
            )
    })
    public ResponseEntity add(@RequestBody ProfileDTO profileDTO) throws URISyntaxException {
        Profile addedProfile = profileService.add(profileMapper.profileDTOToProfile(profileDTO));
        ProfileDTO addedProfileDTO = profileMapper.profileToProfileDTO(addedProfile);
        URI uri = new URI("api/v1/profiles/" + addedProfileDTO.getProfile_id());
        return ResponseEntity.created(uri).body(addedProfileDTO);
    }

    @Operation(summary = "Update a Profile")
    @PutMapping("{id}")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Profile successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Profile not found with supplied ID",
                    content = @Content)
    })
    public ResponseEntity update(@RequestBody ProfileDTO profileDTO,@PathVariable int id){
        Profile profile = profileMapper.profileDTOToProfile(profileDTO);
        Profile updatedProfile = profileService.update(id, profile);
        ProfileDTO updatedProfileDTO = profileMapper.profileToProfileDTO(updatedProfile);
        return ResponseEntity.ok(updatedProfileDTO);
    }

    @Operation(summary = "Delete a Profile")
    @DeleteMapping("{id}")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Profile successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Profile not found with supplied ID",
                    content = @Content)
    })
    public void deleteById(@PathVariable int id) {
        profileService.deleteById(id);
    }

}
