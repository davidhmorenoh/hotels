package com.management.hotels.domain.exceptions.hotels;

import java.util.NoSuchElementException;

public class HotelNotFoundException extends NoSuchElementException {

    public HotelNotFoundException(String message) {
        super(message);
    }

}