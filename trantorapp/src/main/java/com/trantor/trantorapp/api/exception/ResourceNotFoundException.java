package com.trantor.trantorapp.api.exception;

/**
 * ResourceNotFoundException
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
