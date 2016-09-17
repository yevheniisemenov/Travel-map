package com.map.exception;

/**
 * @author Andrew Pasika
 */
public class DuplicatedUserException extends RuntimeException {

    public DuplicatedUserException(String message) {
        super(message);
    }

    public DuplicatedUserException() {
    }

    public DuplicatedUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
