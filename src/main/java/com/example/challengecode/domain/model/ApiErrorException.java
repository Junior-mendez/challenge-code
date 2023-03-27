package com.example.challengecode.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorException extends RuntimeException{
    private String id;
    private String code;
    private String detail;
    public ApiErrorException(String id, String code, String detail){
        this.id=id;
        this.code=code;
        this.detail=detail;
    }
}
