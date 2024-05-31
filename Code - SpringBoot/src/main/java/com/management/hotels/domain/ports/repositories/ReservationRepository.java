package com.management.hotels.domain.ports.repositories;

import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> findByRoom(Room room);

    List<Reservation> findByTraveler(User traveler);

    Reservation save(Reservation reservation);

    List<Reservation> findAll();

    Reservation findById(Long id);

    void delete(Reservation reservation);

}