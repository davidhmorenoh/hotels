package com.management.hotels.infrastructure.adapters.repositories.implementations;

import com.management.hotels.domain.entities.Guest;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.ports.repositories.GuestRepository;
import com.management.hotels.infrastructure.adapters.repositories.jpas.GuestJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImplGuestRepository implements GuestRepository {

    private final GuestJpa guestJpa;

    @Override
    public List<Guest> findByReservation(Reservation reservation) {
        return guestJpa.findByReservation(reservation);
    }

    @Override
    public List<Guest> saveAll(List<Guest> guest) {
        return guestJpa.saveAll(guest);
    }

}