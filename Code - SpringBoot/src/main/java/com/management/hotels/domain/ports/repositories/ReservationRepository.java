package com.management.hotels.domain.ports.repositories;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;

import java.util.Date;
import java.util.List;

public interface ReservationRepository {

    List<Reservation> findByRoom(Room room);

    List<Reservation> findByUser(User traveler);

    List<Reservation> findConflictingReservations(long roomId, Date checkInDate, Date checkOutDate);

    List<Reservation> findAll();

    Reservation findById(Long id);

    Reservation save(Reservation reservation);

    List<Reservation> findByHotel(Hotel hotel);

    void delete(Reservation reservation);

}