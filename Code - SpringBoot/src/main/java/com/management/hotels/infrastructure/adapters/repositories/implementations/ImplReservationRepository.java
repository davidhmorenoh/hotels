package com.management.hotels.infrastructure.adapters.repositories.implementations;

import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.exceptions.ReservationNotFoundException;
import com.management.hotels.domain.ports.repositories.ReservationRepository;
import com.management.hotels.infrastructure.adapters.repositories.jpas.ReservationJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImplReservationRepository implements ReservationRepository {

    private final ReservationJpa reservationJpa;

    @Override
    public List<Reservation> findByRoom(Room room) {
        return reservationJpa.findByRoom(room);
    }

    @Override
    public List<Reservation> findByTraveler(User traveler) {
        return reservationJpa.findByTraveler(traveler);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationJpa.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationJpa.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationJpa.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + id));
    }

    @Override
    public void delete(Reservation reservation) {
        reservationJpa.delete(reservation);
    }

}