package com.management.hotels.infrastructure.adapters.repositories.jpas;

import com.management.hotels.domain.entities.Guest;
import com.management.hotels.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestJpa extends JpaRepository<Guest, Long> {

    List<Guest> findByReservation(Reservation reservation);

}