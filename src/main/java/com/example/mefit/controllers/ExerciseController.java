package com.example.mefit.controllers;

import com.example.mefit.mappers.ExerciseMapper;
import com.example.mefit.models.Exercise;
import com.example.mefit.models.dtos.exercise.ExerciseDTO;
import com.example.mefit.services.exercise.ExerciseService;
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
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(path = "api/v1/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;

    public ExerciseController(ExerciseService exerciseService, ExerciseMapper exerciseMapper) {
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
    }


    @Operation(summary = "Get all Exercises")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ExerciseDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "No Exercises to see",
                    content = @Content)
    })
    public ResponseEntity findall() {
        return ResponseEntity.ok(exerciseMapper.exerciseToExerciseDTO(exerciseService.findAll()));
    }

    @Operation(summary = "Get specific Exercise")
    @GetMapping("{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Exercise does not exist with supplied ID",
                    content = @Content)
    })
    public ResponseEntity findByid(@PathVariable int id){
        return ResponseEntity.ok(exerciseMapper.exerciseToExerciseDTO(exerciseService.findById(id)));
    }

    @Operation(summary = "Add a new Exercise")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = @Content
            )
    })
    public ResponseEntity add(@RequestBody ExerciseDTO exerciseDTO) throws URISyntaxException {
        Exercise addedExercise = exerciseService.add(exerciseMapper.exerciseDTOToExercise(exerciseDTO));
        ExerciseDTO addedExerciseDTO = exerciseMapper.exerciseToExerciseDTO(addedExercise);
        URI uri = new URI("api/v1/exercises/" + addedExerciseDTO.getExercise_id());
        return ResponseEntity.created(uri).body(addedExerciseDTO);
    }

    @Operation(summary = "Update a Exercise")
    @PutMapping("{id}")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Exercise successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Exercise not found with supplied ID",
                    content = @Content)
    })
    public ResponseEntity update(@RequestBody ExerciseDTO exerciseDTO,@PathVariable int id){
        Exercise exercise = exerciseMapper.exerciseDTOToExercise(exerciseDTO);
        Exercise updatedExercise = exerciseService.update(id, exercise);
        ExerciseDTO updatedExerciseDTO = exerciseMapper.exerciseToExerciseDTO(updatedExercise);
        return ResponseEntity.ok(updatedExerciseDTO);
    }

    @Operation(summary = "Delete a Exercise")
    @DeleteMapping("{id}")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Exercise successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Exercise not found with supplied ID",
                    content = @Content)
    })
    public void deleteById(@PathVariable int id) {
        exerciseService.deleteById(id);
    }

}
