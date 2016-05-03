package com.epam.exception;

/**
 * Created by garik on 2/21/16.
 */
public class DatabaseException extends Exception {

    public DatabaseException() {}

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
