package com.example.challengecode.infraestructure.entrypoints.api;

import com.example.challengecode.domain.model.Student;
import com.example.challengecode.domain.usecase.StudentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentUseCase studentUseCase;

    private static final String STATE_ACTIVE = "activo";

    @Operation(summary = "Registrar un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Alumno registrado"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Server Error"),
    })
    @PostMapping("/")
    public Mono<ResponseEntity<Void>> save(@Valid @RequestBody Student student){
        return studentUseCase.save(student).map(s->new ResponseEntity<>(HttpStatus.CREATED));
    }

    @Operation(summary = "Obtener Lista de Alumnos Activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Listado de Alumnos Activos"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Server Error"),
    })
    @GetMapping("/")
    public Mono<ResponseEntity<Flux<Student>>> getAll(){
        return  Mono.just(new ResponseEntity<>(studentUseCase.getAllState(STATE_ACTIVE), HttpStatus.OK));
    }
}
