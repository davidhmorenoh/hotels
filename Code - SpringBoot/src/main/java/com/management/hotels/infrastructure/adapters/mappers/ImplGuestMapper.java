package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.requests.GuestRequest;
import com.management.hotels.application.dtos.responses.GuestResponse;
import com.management.hotels.domain.entities.Guest;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplGuestMapper implements GenericMapper<GuestRequest, GuestResponse, Guest> {

    private final ModelMapper modelMapper;

    @Override
    public GuestResponse toDto(Guest guest) {
        return modelMapper.map(guest, GuestResponse.class);
    }

    @Override
    public Guest toEntity(GuestRequest guestRequest) {
        return modelMapper.map(guestRequest, Guest.class);
    }

}