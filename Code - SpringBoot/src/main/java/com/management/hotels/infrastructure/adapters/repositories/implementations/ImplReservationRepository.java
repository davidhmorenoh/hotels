package com.management.hotels.infrastructure.adapters.repositories.implementations;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.exceptions.reservations.ReservationNotFoundException;
import com.management.hotels.domain.ports.repositories.ReservationRepository;
import com.management.hotels.infrastructure.adapters.repositories.jpas.ReservationJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImplReservationRepository implements ReservationRepository {

    private final ReservationJpa reservationJpa;

    @Override
    public List<Reservation> findAll() {
        return reservationJpa.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationJpa.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + id));
    }

    @Override
    public List<Reservation> findByRoom(Room room) {
        return reservationJpa.findByRoom(room);
    }

    @Override
    public List<Reservation> findByUser(User traveler) {
        return reservationJpa.findByUser(traveler);
    }

    @Override
    public List<Reservation> findByHotel(Hotel hotel) {
        return reservationJpa.findByHotel(hotel);
    }

    @Override
    public List<Reservation> findConflictingReservations(long roomId, Date checkInDate, Date checkOutDate) {
        return reservationJpa.findConflictingReservations(roomId, checkInDate, checkOutDate);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationJpa.save(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationJpa.delete(reservation);
    }

}