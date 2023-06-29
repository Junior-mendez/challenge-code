package com.example.challengecode.domain.usecase;

import com.example.challengecode.domain.model.ApiErrorException;
import com.example.challengecode.domain.model.Student;
import com.example.challengecode.domain.model.gateway.StudentGateway;
import com.example.challengecode.helper.StudentHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class StudentUseCaseTest {

    @Mock
    private StudentGateway studentRepository;
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