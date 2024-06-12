package com.management.hotels.infrastructure.adapters.repositories.implementations;

import com.management.hotels.domain.entities.EmergencyContact;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.ports.repositories.EmergencyContactRepository;
import com.management.hotels.infrastructure.adapters.repositories.jpas.EmergencyContactJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImplEmergencyContactRepository implements EmergencyContactRepository {

    private final EmergencyContactJpa emergencyContactJpa;

    @Override
    public List<EmergencyContact> findByReservation(Reservation reservation) {
        return emergencyContactJpa.findByReservation(reservation);
    }

    @Override
    public List<EmergencyContact> saveAll(List<EmergencyContact> emergencyContacts) {
        return emergencyContactJpa.saveAll(emergencyContacts);
    }

}