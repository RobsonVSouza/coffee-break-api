package com.coffeebreak.exception.handler;

import com.coffeebreak.exception.ExceptionsResponse;
import com.coffeebreak.exception.ProductNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionsResponse> handleAllExceptions(
            Exception ex, WebRequest request) {
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ExceptionsResponse> handleBadRequestExceptions(
            Exception ex, WebRequest request) {
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionsResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        StringBuilder errors = new StringBuilder("Validation failed for fields: ");
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.append(String.format("%s (%s); ", error.getField(), error.getDefaultMessage()));
        }

        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                new Date(),
                errors.toString(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionsResponse, HttpStatus.BAD_REQUEST);
    }
}