package com.example.challengecode.application.config;

import com.example.challengecode.domain.model.gateway.StudentRepository;
import com.example.challengecode.domain.usecase.StudentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public StudentUseCase studentUseCase(StudentRepository studentRepository){
        return new StudentUseCase(studentRepository);
    }
}
