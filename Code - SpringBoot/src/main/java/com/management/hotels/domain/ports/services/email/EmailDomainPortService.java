package com.management.hotels.domain.ports.services.email;

import com.management.hotels.domain.entities.Reservation;

public interface EmailDomainPortService {

    void sendNotificationForNewBooking(Reservation reservation);

    void sendNotificationForBookingUpdated(Reservation reservation);

}