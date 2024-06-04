package com.management.hotels.domain.exceptions.rooms;

public class RoomAlreadyEnabledException extends IllegalStateException {

    public RoomAlreadyEnabledException(String message) {
        super(message);
    }

}