package com.management.hotels.infrastructure.adapters.repositories.implementations;

import com.management.hotels.domain.entities.Guest;
import com.management.hotels.domain.ports.repositories.GuestRepository;
import com.management.hotels.infrastructure.adapters.repositories.jpas.GuestJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ImplGuestRepository implements GuestRepository {

    private final GuestJpa guestJpa;

    @Override
    public Guest save(Guest guest) {
        return guestJpa.save(guest);
    }

}