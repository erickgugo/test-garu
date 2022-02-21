package com.example.demo.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {

    private String errorMessage;
    private String errorCode;
    private Date timestamp;
}
