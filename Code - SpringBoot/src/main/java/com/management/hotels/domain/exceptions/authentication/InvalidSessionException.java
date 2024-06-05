package com.management.hotels.domain.exceptions.authentication;

public class InvalidSessionException extends SecurityException {

    public InvalidSessionException(String message) {
        super(message);
    }

}