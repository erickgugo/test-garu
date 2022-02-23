package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exception(Exception ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setTimestamp(new Date());
        response.setErrorMessage(ex.getMessage());
        if (ex instanceof EntityExistsException) {
            response.setErrorCode("DUPLICATE-EXCEPTION");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        if (ex instanceof EntityNotFoundException) {
            response.setErrorCode("EMPTY-EXCEPTION");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if (ex instanceof DemoException) {
            response.setErrorCode("DEMO-EXCEPTION");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.setErrorCode("EXCEPTION");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
