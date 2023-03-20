package com.example.mefit.controllers;

import com.example.mefit.mappers.ProgramMapper;
import com.example.mefit.models.Goal;
import com.example.mefit.models.dtos.exercise.ExerciseDTO;
import com.example.mefit.models.dtos.goal.GoalDTO;
import com.example.mefit.models.dtos.program.ProgramDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.example.mefit.mappers.GoalMapper;
import com.example.mefit.mappers.ProfileMapper;
import com.example.mefit.services.goal.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1/goals")
public class GoalController {
    private final GoalService goalService;
    private final GoalMapper goalMapper;

    public GoalController(GoalService goalService, GoalMapper goalMapper) {
        this.goalService = goalService;
        this.goalMapper = goalMapper;
    }

    @Operation(summary = "Get all the Goals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GoalDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "No Goals to see",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity findall() {
        return ResponseEntity
                .ok(goalMapper
                        .goalToGoalDTO(goalService.findAll())
                );
    }

    @Operation(summary = "Get a specific Goal")
    @GetMapping("{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GoalDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Goal does not exist with supplied ID",
                    content = @Content)
    })
    public ResponseEntity findByid(@PathVariable int id){
        return ResponseEntity
                .ok(goalMapper
                        .goalToGoalDTO(goalService.findById(id))
                );
    }

    @Operation(summary = "Add a Goal")
    @PostMapping
    public ResponseEntity add(@RequestBody GoalDTO goalDTO) throws URISyntaxException {
        Goal addedGoal = goalService.add(goalMapper.goalDTOToGoal(goalDTO));
        GoalDTO addedGoalDTO = goalMapper.goalToGoalDTO(addedGoal);

        URI uri = new URI("api/v1/goal/" + addedGoalDTO.getGoal_id());
        return ResponseEntity.created(uri).body(addedGoalDTO);

    }

    @Operation(summary = "Update a Goal")
    @PutMapping("{id}")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Goal successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Goal not found with supplied ID",
                    content = @Content)
    })
    public ResponseEntity update(@RequestBody GoalDTO goalDTO,@PathVariable int id){
        Goal goalToUpdate = goalMapper.goalDTOToGoal(goalDTO);
        Goal updatedGoal = goalService.update(id, goalToUpdate);
        GoalDTO updatedGoalDTO = goalMapper.goalToGoalDTO(updatedGoal);
        return ResponseEntity.ok(updatedGoalDTO);
    }

    @Operation(summary = "Delete a Goal")
    @DeleteMapping("{id}")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Goal successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Goal not found with supplied ID",
                    content = @Content)
    })
    public void deleteById(@PathVariable int id) {
        goalService.deleteById(id);
    }

    @Operation(summary = "Get all the programs from a Goal")
    @GetMapping("{id}/programs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "This goal does not have any programs",
                    content = @Content)
    })
    public ResponseEntity getPrograms(@PathVariable int id){
        return ResponseEntity.ok(goalMapper
                .goalToGoalDTO(goalService.findById(id))
                .getPrograms()
        );
    }

}
