package com.management.hotels.domain.services.email;

import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.ports.services.email.EmailDomainPortService;
import com.management.hotels.domain.ports.services.email.NotificationBodyBuilderDomainPortService;
import com.management.hotels.domain.ports.services.email.NotificationEmailDomainPortService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailDomainService implements EmailDomainPortService {

    private final NotificationEmailDomainPortService notificationEmailDomainPortService;
    private final NotificationBodyBuilderDomainPortService notificationBodyBuilderDomainPortService;

    public void sendNotificationForNewBooking(Reservation reservation) {
        String subject = "¡Reserva realizada para " + reservation.getUser().getUsername() + "! Gracias por siempre preferirnos";
        String body = notificationBodyBuilderDomainPortService.newBooking(reservation);
        notificationEmailDomainPortService.sendSimpleMessage(reservation.getUser().getEmail(), subject, body);
    }

    public void sendNotificationForBookingUpdated(Reservation reservation) {
        String subject = "¡Reserva actualizada para " + reservation.getUser().getUsername() + "!";
        String body = notificationBodyBuilderDomainPortService.bookingUpdated(reservation);
        notificationEmailDomainPortService.sendSimpleMessage(reservation.getUser().getEmail(), subject, body);
    }

}