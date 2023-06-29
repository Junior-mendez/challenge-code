package com.example.challengecode.domain.model.gateway;

import com.example.challengecode.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentGateway {

    Mono<Student> save(Student student);
    Mono<Boolean> existsByNameAndLastname(String name, String lastname);
    Flux<Student> getAllState(String state);

    Flux<Student> getAll();
}
