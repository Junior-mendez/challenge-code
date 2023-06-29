package com.example.challengecode.infraestructure.driversadapters.h2adapter.mapper;

import com.example.challengecode.domain.model.Student;
import com.example.challengecode.infraestructure.driversadapters.h2adapter.model.DBStudent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface StudentMapper {
    @Mapping(source = "name",target = "name")
    Student dbStudentToStudent(DBStudent dbStudent);
}
