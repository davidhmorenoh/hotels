package com.management.hotels.infrastructure.adapters.repositories.implementations;

import com.management.hotels.domain.entities.EmergencyContact;
import com.management.hotels.domain.ports.repositories.EmergencyContactRepository;
import com.management.hotels.infrastructure.adapters.repositories.jpas.EmergencyContactJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ImplEmergencyContactRepository implements EmergencyContactRepository {

    private final EmergencyContactJpa emergencyContactJpa;

    @Override
    public EmergencyContact save(EmergencyContact hotel) {
        return emergencyContactJpa.save(hotel);
    }

}