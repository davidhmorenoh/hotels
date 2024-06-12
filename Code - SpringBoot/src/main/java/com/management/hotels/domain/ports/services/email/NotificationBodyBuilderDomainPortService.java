package com.management.hotels.domain.ports.services.email;

import com.management.hotels.domain.entities.Reservation;

public interface NotificationBodyBuilderDomainPortService {

    String newBooking(Reservation reservation);

    String bookingUpdated(Reservation reservation);

}