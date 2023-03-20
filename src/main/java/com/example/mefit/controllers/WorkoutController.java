package com.example.mefit.controllers;

import com.example.mefit.mappers.WorkoutMapper;
import com.example.mefit.mappers.ExerciseMapper;
import com.example.mefit.models.Workout;
import com.example.mefit.models.dtos.exercise.ExerciseDTO;
import com.example.mefit.models.dtos.workout.WorkoutDTO;
import com.example.mefit.services.workout.WorkoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;
    private final WorkoutMapper workoutMapper;

    public WorkoutController(WorkoutService workoutService, WorkoutMapper workoutMapper) {
        this.workoutService = workoutService;
        this.workoutMapper = workoutMapper;
    }


    @Operation(summary = "Get all the Workouts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = WorkoutDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "No Workouts to see",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity findall() {
        return ResponseEntity
                .ok(workoutMapper.workoutToWorkoutDTO(workoutService.findAll())
                );
    }

    @Operation(summary = "Get a specific Workout")
    @GetMapping("{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Workout does not exist with supplied ID",
                    content = @Content)
    })
    public ResponseEntity findByid(@PathVariable int id){
        return ResponseEntity
                .ok(workoutMapper
                        .workoutToWorkoutDTO(workoutService.findById(id))
                );
    }

    @Operation(summary = "Add a Workout")
    @PostMapping
    public ResponseEntity add(@RequestBody WorkoutDTO workoutDTO) throws URISyntaxException {
        Workout addedWorkout = workoutService.add(workoutMapper.workoutDTOToWorkout(workoutDTO));
        WorkoutDTO addedWorkoutDTO = workoutMapper.workoutToWorkoutDTO(addedWorkout);

        URI uri = new URI("api/v1/workout/" + addedWorkoutDTO.getWorkout_id());
        return ResponseEntity.created(uri).body(addedWorkoutDTO);

    }

    @Operation(summary = "Update a Workout")
    @PutMapping("{id}")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Workout successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Workout not found with supplied ID",
                    content = @Content)
    })
    public ResponseEntity update(@RequestBody WorkoutDTO workoutDTO,@PathVariable int id){
        Workout workoutToUpdate = workoutMapper.workoutDTOToWorkout(workoutDTO);
        Workout updatedWorkout = workoutService.update(id, workoutToUpdate);
        WorkoutDTO updatedWorkoutDTO = workoutMapper.workoutToWorkoutDTO(updatedWorkout);
        return ResponseEntity.ok(updatedWorkoutDTO);
    }

    @Operation(summary = "Delete a Workout")
    @DeleteMapping("{id}")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Workout successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Workout not found with supplied ID",
                    content = @Content)
    })
    public void deleteById(@PathVariable int id) {
        workoutService.deleteById(id);
    }

    @Operation(summary = "Get all the exercises from a Workout")
    @GetMapping("{id}/exercises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "This workout does not have any exercises",
                    content = @Content)
    })
    public ResponseEntity getExercises(@PathVariable int id){
        return ResponseEntity.ok(workoutMapper
                .workoutToWorkoutDTO(workoutService.findById(id))
                .getExercises()
        );
    }

}
