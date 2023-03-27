package com.example.challengecode.infraestructure.driversadapters.h2adapter.gateway;

import com.example.challengecode.domain.model.Student;
import com.example.challengecode.domain.model.gateway.StudentRepository;
import com.example.challengecode.infraestructure.driversadapters.h2adapter.StudentDBRepository;
import com.example.challengecode.infraestructure.driversadapters.h2adapter.mapper.StudentMapper;
import com.example.challengecode.infraestructure.driversadapters.h2adapter.model.DBStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StudentGatewayImpl implements StudentRepository {

    private final StudentDBRepository studentDBRepository;

    private final StudentMapper studentMapper;
    @Override
    public Mono<Student> save(Student student) {
       return studentDBRepository.save(DBStudent.builder().name(student.getName())
                       .lastname(student.getLastname()).age(student.getAge())
                       .state(student.getState()).build()).
               map(studentMapper::dbStudentToStudent);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return studentDBRepository.existsById(id);
    }

    @Override
    public Flux<Student> getAllState(String state) {
        return studentDBRepository.findAllByState(state).map(studentMapper::dbStudentToStudent);
    }
}
