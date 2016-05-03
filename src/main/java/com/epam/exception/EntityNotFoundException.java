package com.epam.exception;

/**
 * Created by garik on 2/21/16.
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException() {}

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
