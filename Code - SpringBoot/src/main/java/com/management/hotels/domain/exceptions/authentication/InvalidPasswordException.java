package com.management.hotels.domain.exceptions.authentication;

public class InvalidPasswordException extends IllegalArgumentException {

    public InvalidPasswordException(String message) {
        super(message);
    }

}