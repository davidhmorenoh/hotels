package com.management.hotels.domain.ports.repositories;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;

import java.util.Date;
import java.util.List;

public interface ReservationRepository {

    List<Reservation> findAll();

    Reservation findById(Long id);

    List<Reservation> findByRoom(Room room);

    List<Reservation> findByUser(User traveler);

    List<Reservation> findByHotel(Hotel hotel);

    List<Reservation> findConflictingReservations(long roomId, Date checkInDate, Date checkOutDate);

    Reservation save(Reservation reservation);

    void delete(Reservation reservation);

}