package com.management.hotels.domain.exceptions.users;

public class UserAlreadyRegisteredException extends IllegalStateException {

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }

}