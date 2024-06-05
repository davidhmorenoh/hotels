package com.management.hotels.domain.ports.repositories;

import com.management.hotels.domain.entities.Guest;

public interface GuestRepository {

    Guest save(Guest guest);

}