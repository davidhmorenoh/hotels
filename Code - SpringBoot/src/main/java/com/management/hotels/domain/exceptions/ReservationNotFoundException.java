package com.management.hotels.domain.exceptions;

import java.util.NoSuchElementException;

public class ReservationNotFoundException extends NoSuchElementException {

    public ReservationNotFoundException(String message) {
        super(message);
    }

}