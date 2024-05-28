package com.management.hotels.domain.services;

import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.infrastructure.adapters.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByRoom(Room room) {
        return reservationRepository.findByRoom(room);
    }

    public List<Reservation> getReservationsByTraveler(User traveler) {
        return reservationRepository.findByTraveler(traveler);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    public Reservation updateReservationStatus(Long reservationId, Status status) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus(status);
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservationRepository.delete(reservation);
    }

}