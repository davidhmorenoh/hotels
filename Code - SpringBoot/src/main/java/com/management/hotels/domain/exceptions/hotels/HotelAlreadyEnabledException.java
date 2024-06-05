package com.management.hotels.domain.exceptions.hotels;

public class HotelAlreadyEnabledException extends IllegalStateException {

    public HotelAlreadyEnabledException(String message) {
        super(message);
    }

}