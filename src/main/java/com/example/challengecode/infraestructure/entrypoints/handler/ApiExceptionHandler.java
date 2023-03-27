package com.example.challengecode.infraestructure.entrypoints.handler;

import com.example.challengecode.domain.model.ApiErrorException;
import com.example.challengecode.domain.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiErrorException.class)
    public Mono<ResponseEntity<ApiResponse>> apiErrorException(ApiErrorException apiErrorException){
        return Mono.just(new ResponseEntity<>(new ApiResponse(apiErrorException.getCode(),apiErrorException.getDetail()), HttpStatus.valueOf(Integer.valueOf(apiErrorException.getCode()))));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Map<String,String>> handleWebExchangeBindException(WebExchangeBindException webExchangeBindException){
        Map<String,String> resp = new HashMap<>();
        webExchangeBindException.getBindingResult()
                .getAllErrors()
                .forEach(error->
                    resp.put(((FieldError) error).getField(), error.getDefaultMessage())
                );
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}
