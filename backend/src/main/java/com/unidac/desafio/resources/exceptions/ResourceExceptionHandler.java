package com.unidac.desafio.resources.exceptions;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import com.unidac.desafio.services.exceptions.InvalidBreakfastDateException;
import com.unidac.desafio.services.exceptions.InvalidBreakfastParticipationException;
import com.unidac.desafio.services.exceptions.ResourceAlreadyExistsException;
import com.unidac.desafio.services.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<StandardError> resourceNotFound(
            ResourceAlreadyExistsException e, HttpServletRequest request) {
        String error = "Resource already exists";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err =
                new StandardError(
                        Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidBreakfastParticipationException.class)
    public ResponseEntity<StandardError> invalidBreakfastParticipation(
            InvalidBreakfastParticipationException e, HttpServletRequest request) {
        String error = "Invalid breakfast participation";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err =
                new StandardError(
                        Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public Map<String, String> handleInvalidDateFormat(DateTimeParseException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(exception.getClass().toString(), exception.getMessage());
        return errorMap;
    }

    @ExceptionHandler(InvalidBreakfastDateException.class)
    public ResponseEntity<StandardError> handleInvalidBreakfastDate(
            InvalidBreakfastDateException e, HttpServletRequest request) {
        String error = "Invalid breakfast date";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err =
                new StandardError(
                        Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}