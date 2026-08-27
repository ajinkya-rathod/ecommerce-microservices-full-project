package com.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<Map<String,Object>> HandleExist(ResourceAlreadyExistException ex){
        Map<String,Object> m= Map.of("error","conflict","message",ex.getMessage());
       return ResponseEntity.status(HttpStatus.CONFLICT).body(m);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> HandleNotFound(ResourceNotFoundException ex){
        Map<String,Object> m= Map.of("error","Not Found","message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> HandleValidation(MethodArgumentNotValidException ex){
        Map<String,Object> error = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> error.put(err.getField(),err.getDefaultMessage()));

        Map<String,Object> body = Map.of("error","validation failed ","details",error);

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleAll(Exception ex){

        Map<String,Object> m= Map.of("error","internal server error ","message",ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(m);

    }
}
