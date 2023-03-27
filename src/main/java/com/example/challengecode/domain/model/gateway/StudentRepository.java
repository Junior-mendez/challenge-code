package com.example.challengecode.domain.model.gateway;

import com.example.challengecode.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository {

    Mono<Student> save(Student student);
    Mono<Boolean> existsById(Long id);
    Flux<Student> getAllState(String state);
}
