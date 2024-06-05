package com.management.hotels.domain.exceptions.hotels;

public class HotelAlreadyDisabledException extends IllegalStateException {

    public HotelAlreadyDisabledException(String message) {
        super(message);
    }

}