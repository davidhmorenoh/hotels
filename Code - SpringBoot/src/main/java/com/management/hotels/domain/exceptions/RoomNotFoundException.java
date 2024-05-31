package com.management.hotels.domain.exceptions;

import java.util.NoSuchElementException;

public class RoomNotFoundException extends NoSuchElementException {

    public RoomNotFoundException(String message) {
        super(message);
    }

}