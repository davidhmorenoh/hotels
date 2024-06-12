package com.management.hotels.domain.services.email;

import com.management.hotels.domain.entities.EmergencyContact;
import com.management.hotels.domain.entities.Guest;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.ports.services.email.NotificationBodyBuilderDomainPortService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NotificationBodyBuilderDomainService implements NotificationBodyBuilderDomainPortService {

    @Override
    public String newBooking(Reservation reservation) {
        return buildReservationBody("Aquí el detalle de tu reserva:\n\n", reservation);
    }

    @Override
    public String bookingUpdated(Reservation reservation) {
        return buildReservationBody("Aquí el detalle de la actualización de tu reserva:\n\n", reservation);
    }

    private String buildReservationBody(String header, Reservation reservation) {
        StringBuilder body;
        body = new StringBuilder(header)
                .append("Consecutivo: ").append(reservation.getReservationId()).append("\n")
                .append("Fecha: ").append(reservation.getReservationDate()).append("\n")
                .append("Estado: ").append(reservation.getState()).append("\n")
                .append("Hotel: ").append(reservation.getRoom().getHotel().getHotelName()).append("\n")
                .append("Ciudad: ").append(reservation.getRoom().getHotel().getCity()).append("\n")
                .append("Dirección: ").append(reservation.getRoom().getHotel().getAddress()).append("\n")
                .append("Habitación: ").append(reservation.getRoom().getLocation()).append("\n")
                .append("Tipo de habitación: ").append(reservation.getRoom().getRoomType()).append("\n")
                .append(guestsInformation(reservation.getGuests()))
                .append(emergencyContactsInformation(reservation.getEmergencyContacts()));
        return body.toString();
    }

    private String guestsInformation(List<Guest> guests) {
        StringBuilder guestsInformation = new StringBuilder("Huéspedes:\n");
        int guestNumber = 1;
        for (Guest guest : guests) {
            guestsInformation.append("\tHuésped N°").append(guestNumber).append("\n")
                    .append("\t\tNombre completo: ").append(guest.getFirstName()).append(" ").append(guest.getLastName()).append("\n")
                    .append("\t\tFecha de nacimiento: ").append(guest.getDateOfBirth()).append("\n")
                    .append("\t\tGénero: ").append(guest.getGender()).append("\n")
                    .append("\t\tTipo de documento: ").append(guest.getDocumentType()).append("\n")
                    .append("\t\tNúmero de documento: ").append(guest.getDocumentNumber()).append("\n");
            if (Objects.nonNull(guest.getEmail())) {
                guestsInformation.append("\t\tCorreo electrónico: ").append(guest.getEmail()).append("\n");
            }
            if (Objects.nonNull(guest.getContactPhone())) {
                guestsInformation.append("\t\tTeléfono de contacto: ").append(guest.getContactPhone()).append("\n");
            }
            guestNumber++;
        }
        return guestsInformation.toString();
    }

    private String emergencyContactsInformation(List<EmergencyContact> emergencyContacts) {
        StringBuilder emergencyContactsInformation = new StringBuilder("Contactos de emergencia:\n");
        int emergencyContactNumber = 1;
        for (EmergencyContact emergencyContact : emergencyContacts) {
            emergencyContactsInformation.append("\tContacto de emergencia N°").append(emergencyContactNumber).append("\n")
                    .append("\t\tNombre completo: ").append(emergencyContact.getFullName()).append("\n")
                    .append("\t\tTeléfono de contacto: ").append(emergencyContact.getContactPhone()).append("\n");
            emergencyContactNumber++;
        }
        return emergencyContactsInformation.toString();
    }

}