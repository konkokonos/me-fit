package com.example.mefit.controllers;

import com.example.mefit.mappers.ProgramMapper;
import com.example.mefit.mappers.WorkoutMapper;
import com.example.mefit.models.Program;
import com.example.mefit.models.dtos.program.ProgramDTO;
import com.example.mefit.services.program.ProgramService;
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
@RequestMapping(path = "/api/v1/programs")
public class ProgramController {
    private final ProgramService programService;
    private final ProgramMapper programMapper;

    public ProgramController(ProgramService programService, ProgramMapper programMapper, WorkoutMapper workoutMapper) {
        this.programService = programService;
        this.programMapper = programMapper;
    }


    @Operation(summary = "Get all the Programs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProgramDTO.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "No Programs to see",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity findall() {
        return ResponseEntity
                .ok(programMapper.programToProgramDTO(programService.findAll())
                );
    }

    @Operation(summary = "Get a specific Program")
    @GetMapping("{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProgramDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Program does not exist with supplied ID",
                    content = @Content)
    })
    public ResponseEntity findByid(@PathVariable int id) {
        return ResponseEntity
                .ok(programMapper
                        .programToProgramDTO(programService.findById(id))
                );
    }

    @Operation(summary = "Add a Program")
    @PostMapping
    public ResponseEntity add(@RequestBody ProgramDTO programDTO) throws URISyntaxException {
        Program addedProgram = programService.add(programMapper.programDTOToProgram(programDTO));
        ProgramDTO addedProgramDTO = programMapper.programToProgramDTO(addedProgram);

        URI uri = new URI("api/v1/program/" + addedProgramDTO.getProgram_id());
        return ResponseEntity.created(uri).body(addedProgramDTO);

    }

    @Operation(summary = "Update a Program")
    @PutMapping("{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Program successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Program not found with supplied ID",
                    content = @Content)
    })
    public ResponseEntity update(@RequestBody ProgramDTO programDTO, @PathVariable int id) {
        Program programToUpdate = programMapper.programDTOToProgram(programDTO);
        Program updatedProgram = programService.update(id, programToUpdate);
        ProgramDTO updatedProgramDTO = programMapper.programToProgramDTO(updatedProgram);
        return ResponseEntity.ok(updatedProgramDTO);
    }

    @Operation(summary = "Delete a Program")
    @DeleteMapping("{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Program successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Program not found with supplied ID",
                    content = @Content)
    })
    public void deleteById(@PathVariable int id) {
        programService.deleteById(id);
    }

    @Operation(summary = "Get all the workouts from a Program")
    @GetMapping("{id}/workouts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "This program does not have any workouts",
                    content = @Content)
    })
    public ResponseEntity getWorkouts(@PathVariable int id){
        return ResponseEntity.ok(programMapper
                .programToProgramDTO(programService.findById(id))
                .getWorkouts()
        );
    }

}
