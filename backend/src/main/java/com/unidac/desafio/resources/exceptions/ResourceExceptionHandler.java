package com.unidac.desafio.resources.exceptions;

import java.time.Instant;

import com.unidac.desafio.services.exceptions.InvalidBreakfastParticipationException;
import com.unidac.desafio.services.exceptions.ResourceAlreadyExistsException;
import com.unidac.desafio.services.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
}