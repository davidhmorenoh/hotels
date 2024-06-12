package com.management.hotels.domain.ports.services;

import com.management.hotels.domain.entities.Guest;
import com.management.hotels.domain.entities.Reservation;

import java.util.List;

public interface GuestDomainPortService {

    List<Guest> getGuestsByReservation(Reservation reservation);

    List<Guest> assignReservationAndSaveGuests(List<Guest> guests, Reservation reservation);

}