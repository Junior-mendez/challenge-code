package com.example.challengecode.domain.usecase;

import com.example.challengecode.domain.model.ApiErrorException;
import com.example.challengecode.domain.model.Student;
import com.example.challengecode.domain.model.gateway.StudentGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StudentUseCase {
    private final StudentGateway studentGateway;

    public Mono<Student> save(Student student){
        return studentGateway.existsByNameAndLastname(student.getName(),student.getLastname())
                .flatMap(exists-> (exists) ? Mono.error(new ApiErrorException(student.getName().concat(" ").concat(student.getLastname())
                        , "500", "El alumno ya se encuentra registrado"))
                    : studentGateway.save(student));
    }

    public Flux<Student> getAllState(String state){
        return studentGateway.getAllState(state);
    }

}
