package com.example.challengecode.infraestructure.driversadapters.h2adapter;

import com.example.challengecode.infraestructure.driversadapters.h2adapter.model.DBStudent;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface StudentDBRepository extends R2dbcRepository<DBStudent, Long> {

    Flux<DBStudent> findAllByState(String state);
}
