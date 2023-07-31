package com.unidac.desafio.services.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String identifierName, Object identifier) {
        super("Resource already exists. " + identifierName + ": " + identifier);
    }
}
