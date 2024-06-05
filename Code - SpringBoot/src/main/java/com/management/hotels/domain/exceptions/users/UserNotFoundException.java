package com.management.hotels.domain.exceptions.users;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {

    public UserNotFoundException(String message) {
        super(message);
    }

}