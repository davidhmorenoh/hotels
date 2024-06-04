package com.management.hotels.domain.exceptions.rooms;

import java.util.NoSuchElementException;

public class RoomNotFoundException extends NoSuchElementException {

    public RoomNotFoundException(String message) {
        super(message);
    }

}