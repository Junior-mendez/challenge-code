package com.example.challengecode.helper;

import com.example.challengecode.domain.model.Student;

public class StudentHelper {

    public static  Student createStudent(){
        return new Student(1L, "Junior", "Mendez", "activo", 27);
    }

    public static  Student createStudentBad(){
        return new Student(1L, "Ju", "Me", "active", 0);
    }
}
