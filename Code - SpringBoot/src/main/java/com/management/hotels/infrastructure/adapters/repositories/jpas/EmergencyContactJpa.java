package com.management.hotels.infrastructure.adapters.repositories.jpas;

import com.management.hotels.domain.entities.EmergencyContact;
import com.management.hotels.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmergencyContactJpa extends JpaRepository<EmergencyContact, Long> {

    List<EmergencyContact> findByReservation(Reservation reservation);

}