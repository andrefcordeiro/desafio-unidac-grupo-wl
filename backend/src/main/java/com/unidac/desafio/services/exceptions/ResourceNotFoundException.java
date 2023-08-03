package com.unidac.desafio.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String identifierName, Object identifier) {
        super("Resource not found. " + identifierName + ": " + identifier);
    }
}
