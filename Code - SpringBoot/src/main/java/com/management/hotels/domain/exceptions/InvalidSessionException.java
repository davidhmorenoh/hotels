package com.management.hotels.domain.exceptions;

public class InvalidSessionException extends SecurityException {

    public InvalidSessionException(String message) {
        super(message);
    }

}