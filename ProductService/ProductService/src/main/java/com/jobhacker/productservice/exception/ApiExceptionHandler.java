package com.jobhacker.productservice.exception;

import com.jobhacker.productservice.util.ErrorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorModel> apiExceptionHandler(ApiBaseExceptionHandling exception, WebRequest webRequest) {
        ErrorModel errorModel = ErrorModel.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .urlFailurePath(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorModel, exception.getStatusCode());
    }
}
