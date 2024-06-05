package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.requests.EmergencyContactRequest;
import com.management.hotels.application.dtos.responses.EmergencyContactResponse;
import com.management.hotels.domain.entities.EmergencyContact;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplEmergencyContactsMapper implements GenericMapper<EmergencyContactRequest, EmergencyContactResponse, EmergencyContact> {

    private final ModelMapper modelMapper;

    @Override
    public EmergencyContactResponse toDto(EmergencyContact emergencyContact) {
        return modelMapper.map(emergencyContact, EmergencyContactResponse.class);
    }

    @Override
    public EmergencyContact toEntity(EmergencyContactRequest emergencyContactRequest) {
        return modelMapper.map(emergencyContactRequest, EmergencyContact.class);
    }

}