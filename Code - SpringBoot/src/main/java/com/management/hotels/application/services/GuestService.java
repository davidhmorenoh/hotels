package com.management.hotels.application.services;

import com.management.hotels.application.dtos.requests.GuestRequest;
import com.management.hotels.application.dtos.responses.GuestResponse;
import com.management.hotels.domain.entities.Guest;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.ports.repositories.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    private final GenericMapper<GuestRequest, GuestResponse, Guest> guestMapper;

    public List<GuestResponse> createGuest(List<GuestRequest> guestRequests, Reservation reservation) {
        List<Guest> guests = guestRequests.stream().map(guestMapper::toEntity).collect(Collectors.toList());

        guests.forEach(guest -> guest.setReservation(reservation));

        guests = guests.stream().map(guestRepository::save).toList();
        return guests.stream().map(guestMapper::toDto).collect(Collectors.toList());
    }

}