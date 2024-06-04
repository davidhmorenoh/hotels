package com.management.hotels.domain.exceptions.rooms;

public class RoomAlreadyDisabledException extends IllegalStateException {

    public RoomAlreadyDisabledException(String message) {
        super(message);
    }

}