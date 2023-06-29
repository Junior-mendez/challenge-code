package com.example.challengecode.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorException extends RuntimeException{
    private String alumno;
    private String code;
    private String detail;
    public ApiErrorException(String alumno, String code, String detail){
        this.alumno=alumno;
        this.code=code;
        this.detail=detail;
    }
}
