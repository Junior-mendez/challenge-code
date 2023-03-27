package com.example.challengecode.domain.usecase;

import com.example.challengecode.domain.model.ApiErrorException;
import com.example.challengecode.domain.model.Student;
import com.example.challengecode.domain.model.gateway.StudentRepository;
import com.example.challengecode.helper.StudentHelper;
import com.example.challengecode.infraestructure.driversadapters.h2adapter.gateway.StudentGatewayImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class StudentUseCaseTest {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentUseCase studentUseCase;

    public static final Student student = StudentHelper.createStudent();

    @Test
    void save() {
        BDDMockito.when(studentRepository.save(student)).thenReturn(Mono.just(student));
        BDDMockito.when(studentRepository.existsById(Mockito.anyLong())).thenReturn(Mono.just(Boolean.FALSE));
       StepVerifier.create(studentUseCase.save(student))
               .expectSubscription()
               .expectNext(student)
               .verifyComplete();
    }

    @Test
    void getAll() {
        BDDMockito.when(studentRepository.getAllState(Mockito.anyString())).thenReturn(Flux.just(student));
        StepVerifier.create(studentUseCase.getAllState("activo"))
                .expectSubscription()
                .expectNext(student)
                .verifyComplete();
    }
    @Test
    void saveStateFailed() {
        BDDMockito.when(studentRepository.existsById(Mockito.anyLong())).thenReturn(Mono.just(Boolean.TRUE));
        StepVerifier.create(studentUseCase.save(student))
                .expectSubscription()
                .expectError(ApiErrorException.class)
                .verify();
    }

}