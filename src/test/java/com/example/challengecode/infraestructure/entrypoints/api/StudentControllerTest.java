package com.example.challengecode.infraestructure.entrypoints.api;

import com.example.challengecode.domain.model.ApiErrorException;
import com.example.challengecode.domain.model.ApiResponse;
import com.example.challengecode.domain.model.Student;
import com.example.challengecode.domain.usecase.StudentUseCase;
import com.example.challengecode.helper.StudentHelper;
import net.bytebuddy.asm.MemberSubstitution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    private Validator validator;
    @Mock
    private StudentUseCase studentUseCase;


    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void getAllStudentTest(){
        when(studentUseCase.getAllState(Mockito.anyString())).thenReturn(Flux.just(StudentHelper.createStudent()) );
        Mono<ResponseEntity<Flux<Student>>> response =  studentController.getAll();
        assertEquals(response.block().getStatusCode(), HttpStatus.OK);
        Flux<Student> students = response.block().getBody();
        assertEquals("activo",students.blockFirst().getState());

    }

    @Test
    void saveStudentTest(){
        Student student = StudentHelper.createStudent();
        when(studentUseCase.save(student)).thenReturn(Mono.just(student));
        Mono<ResponseEntity<Void>> response =  studentController.save(student);
        assertEquals(HttpStatus.CREATED, response.block().getStatusCode());
    }

    @Test
    void saveStudentValidateTest(){
        Student student = StudentHelper.createStudentBad();
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertEquals(4,violations.size());
    }

}