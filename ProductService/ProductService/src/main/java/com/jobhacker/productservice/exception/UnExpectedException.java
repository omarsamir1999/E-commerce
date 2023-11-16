package com.jobhacker.productservice.exception;

import org.springframework.http.HttpStatus;

public class UnExpectedException extends ApiBaseExceptionHandling {

    public UnExpectedException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
