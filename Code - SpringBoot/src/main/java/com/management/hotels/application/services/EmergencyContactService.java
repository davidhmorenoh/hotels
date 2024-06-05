package com.management.hotels.application.services;

import com.management.hotels.application.dtos.requests.EmergencyContactRequest;
import com.management.hotels.application.dtos.responses.EmergencyContactResponse;
import com.management.hotels.domain.entities.EmergencyContact;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.ports.repositories.EmergencyContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmergencyContactService {

    private final EmergencyContactRepository emergencyContactRepository;

    private final GenericMapper<EmergencyContactRequest, EmergencyContactResponse, EmergencyContact> emergencyContactMapper;

    public List<EmergencyContactResponse> createEmergencyContacts(List<EmergencyContactRequest> emergencyContactRequests, Reservation reservation) {
        List<EmergencyContact> emergencyContacts = emergencyContactRequests.stream().map(emergencyContactMapper::toEntity).collect(Collectors.toList());

        emergencyContacts.forEach(emergencyContact -> emergencyContact.setReservation(reservation));

        emergencyContacts = emergencyContacts.stream().map(emergencyContactRepository::save).toList();
        return emergencyContacts.stream().map(emergencyContactMapper::toDto).collect(Collectors.toList());
    }

}