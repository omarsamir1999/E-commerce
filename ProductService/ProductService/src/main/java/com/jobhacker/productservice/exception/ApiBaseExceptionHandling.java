package com.jobhacker.productservice.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiBaseExceptionHandling extends RuntimeException {

    public ApiBaseExceptionHandling(String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();
}
