package com.management.hotels.domain.exceptions;

public class UserAlreadyRegisteredException extends IllegalStateException {

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }

}