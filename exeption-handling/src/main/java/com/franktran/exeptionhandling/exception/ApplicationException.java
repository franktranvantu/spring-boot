package com.franktran.exeptionhandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationException {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> studentNotFound(StudentNotFoundException exception) {
        return new ResponseEntity<>(String.format("Student with id %d not found", exception.getId()), HttpStatus.NOT_FOUND);
    }
}
