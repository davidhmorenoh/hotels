package com.management.hotels.domain.services;

import com.management.hotels.domain.entities.EmergencyContact;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.ports.repositories.EmergencyContactRepository;
import com.management.hotels.domain.ports.services.EmergencyContactDomainPortService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergencyContactDomainService implements EmergencyContactDomainPortService {

    private final EmergencyContactRepository emergencyContactRepository;

    @Override
    public List<EmergencyContact> getEmergencyContactsByReservation(Reservation reservation) {
        return emergencyContactRepository.findByReservation(reservation);
    }

    @Override
    public List<EmergencyContact> assignReservationAndSaveContacts(List<EmergencyContact> emergencyContacts, Reservation reservation) {
        emergencyContacts.forEach(emergencyContact -> emergencyContact.setReservation(reservation));
        return emergencyContactRepository.saveAll(emergencyContacts);
    }

}