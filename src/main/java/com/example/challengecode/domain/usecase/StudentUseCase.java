package com.example.challengecode.domain.usecase;

import com.example.challengecode.domain.model.ApiErrorException;
import com.example.challengecode.domain.model.Student;
import com.example.challengecode.domain.model.gateway.StudentRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StudentUseCase {
    private final StudentRepository studentRepository;

    public Mono<Student> save(Student student){
        return studentRepository.existsById(student.getId())
                .flatMap(exists-> (exists) ? Mono.error(new ApiErrorException(student.getId().toString(), "500", "El alumno ya se encuentra registrado"))
                    : studentRepository.save(student));
    }

    public Flux<Student> getAllState(String state){
        return studentRepository.getAllState(state);
    }


}
