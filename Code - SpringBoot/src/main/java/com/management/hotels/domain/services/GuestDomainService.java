package com.management.hotels.domain.services;

import com.management.hotels.domain.entities.Guest;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.ports.repositories.GuestRepository;
import com.management.hotels.domain.ports.services.GuestDomainPortService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestDomainService implements GuestDomainPortService {

    private final GuestRepository guestRepository;

    @Override
    public List<Guest> getGuestsByReservation(Reservation reservation) {
        return guestRepository.findByReservation(reservation);
    }

    @Override
    public List<Guest> assignReservationAndSaveGuests(List<Guest> guests, Reservation reservation) {
        guests.forEach(guest -> guest.setReservation(reservation));
        return guestRepository.saveAll(guests);
    }

}