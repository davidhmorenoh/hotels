package com.management.hotels.domain.ports.repositories;

import com.management.hotels.domain.entities.Guest;
import com.management.hotels.domain.entities.Reservation;

import java.util.List;

public interface GuestRepository {

    List<Guest> findByReservation(Reservation reservation);

    List<Guest> saveAll(List<Guest> guests);

}