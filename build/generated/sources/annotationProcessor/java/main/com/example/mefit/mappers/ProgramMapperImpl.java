package com.example.mefit.mappers;

import com.example.mefit.models.Program;
import com.example.mefit.models.dtos.program.ProgramDTO;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-20T11:10:44+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class ProgramMapperImpl extends ProgramMapper {

    @Override
    public ProgramDTO programToProgramDTO(Program program) {
        if ( program == null ) {
            return null;
        }

        ProgramDTO programDTO = new ProgramDTO();

        programDTO.setWorkouts( mapWorkout( program.getWorkouts() ) );
        programDTO.setGoals( mapGoal( program.getGoals() ) );
        if ( program.getProgram_id() != null ) {
            programDTO.setProgram_id( program.getProgram_id() );
        }
        programDTO.setProgram_name( program.getProgram_name() );
        programDTO.setCategory( program.getCategory() );
        programDTO.setComplete_program( program.isComplete_program() );

        return programDTO;
    }

    @Override
    public Collection<ProgramDTO> programToProgramDTO(Collection<Program> program) {
        if ( program == null ) {
            return null;
        }

        Collection<ProgramDTO> collection = new ArrayList<ProgramDTO>( program.size() );
        for ( Program program1 : program ) {
            collection.add( programToProgramDTO( program1 ) );
        }

        return collection;
    }

    @Override
    public Program programDTOToProgram(ProgramDTO programDTO) {
        if ( programDTO == null ) {
            return null;
        }

        Program program = new Program();

        program.setWorkouts( mapIdsToWorkout( programDTO.getWorkouts() ) );
        program.setGoals( mapIdsToGoal( programDTO.getGoals() ) );
        program.setProgram_id( programDTO.getProgram_id() );
        program.setProgram_name( programDTO.getProgram_name() );
        program.setCategory( programDTO.getCategory() );
        if ( programDTO.getComplete_program() != null ) {
            program.setComplete_program( programDTO.getComplete_program() );
        }

        return program;
    }
}
