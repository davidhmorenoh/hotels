package com.management.hotels.domain.exceptions.reservations;

public class BookingPreconditionsException extends IllegalArgumentException {

    public BookingPreconditionsException(String message) {
        super(message);
    }

}