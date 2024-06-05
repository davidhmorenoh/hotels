package com.management.hotels.domain.exceptions.reservations;

public class ReservationAlreadyCancelledException extends IllegalStateException {

    public ReservationAlreadyCancelledException(String message) {
        super(message);
    }

}