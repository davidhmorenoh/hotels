package com.management.hotels.application.services;

import com.management.hotels.application.dtos.responses.EmergencyContactResponse;
import com.management.hotels.application.dtos.responses.GuestResponse;
import com.management.hotels.application.dtos.responses.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendNotificationForNewBooking(String toEmail, ReservationResponse reservationResponse) {
        String subject = "¡Reserva realizada para " + reservationResponse.getUser().getUsername() + "! Gracias por siempre preferirnos";
        String body = "Aquí el detalle de tu reserva:\n\n" +
                "Consecutivo: " + reservationResponse.getReservationId() + "\n" +
                "Fecha: " + reservationResponse.getReservationDate() + "\n" +
                "Estado: " + reservationResponse.getState() + "\n" +
                "Hotel: " + reservationResponse.getRoom().getHotel().getHotelName() + "\n" +
                "Ciudad: " + reservationResponse.getRoom().getHotel().getCity() + "\n" +
                "Dirección: " + reservationResponse.getRoom().getHotel().getAddress() + "\n" +
                "Habitación: " + reservationResponse.getRoom().getLocation() + "\n" +
                "Tipo de habitación: " + reservationResponse.getRoom().getRoomType() + "\n" +
                this.guestsInformation(reservationResponse.getGuests()) +
                this.emergencyContactsInformation(reservationResponse.getEmergencyContacts());
        this.sendSimpleEmail(toEmail, subject, body);
    }

    public void sendNotificationForBookingUpdated(String toEmail, ReservationResponse reservationResponse) {
        String subject = "¡Reserva actualizada para " + reservationResponse.getUser().getUsername() + "!";
        String body = "Aquí el detalle de la actualización de tu reserva:\n\n" +
                "Consecutivo: " + reservationResponse.getReservationId() + "\n" +
                "Fecha: " + reservationResponse.getReservationDate() + "\n" +
                "Estado: " + reservationResponse.getState() + "\n" +
                "Hotel: " + reservationResponse.getRoom().getHotel().getHotelName() + "\n" +
                "Ciudad: " + reservationResponse.getRoom().getHotel().getCity() + "\n" +
                "Dirección: " + reservationResponse.getRoom().getHotel().getAddress() + "\n" +
                "Habitación: " + reservationResponse.getRoom().getLocation() + "\n" +
                "Tipo de habitación: " + reservationResponse.getRoom().getRoomType() + "\n" +
                this.guestsInformation(reservationResponse.getGuests()) +
                this.emergencyContactsInformation(reservationResponse.getEmergencyContacts());
        this.sendSimpleEmail(toEmail, subject, body);
    }

    private String guestsInformation(List<GuestResponse> guestResponses) {
        StringBuilder guestsInformation = new StringBuilder("Huéspedes:\n");
        int guest = 1;
        for (GuestResponse guestResponse : guestResponses) {
            guestsInformation.append("\tHuésped N°").append(guest).append("\n");
            guestsInformation.append("\t\tNombre completo: ").append(guestResponse.getFirstName()).append(" ").append(guestResponse.getLastName()).append("\n");
            guestsInformation.append("\t\tFecha de nacimiento: ").append(guestResponse.getDateOfBirth()).append("\n");
            guestsInformation.append("\t\tGénero: ").append(guestResponse.getGender()).append("\n");
            guestsInformation.append("\t\tTipo de documento: ").append(guestResponse.getDocumentType()).append("\n");
            guestsInformation.append("\t\tNúmero de documento: ").append(guestResponse.getDocumentNumber()).append("\n");
            if (Objects.nonNull(guestResponse.getEmail())) {
                guestsInformation.append("\t\tCorreo electrónico: ").append(guestResponse.getEmail()).append("\n");
            }
            if (Objects.nonNull(guestResponse.getContactPhone())) {
                guestsInformation.append("\t\tTeléfono de contacto: ").append(guestResponse.getContactPhone()).append("\n");
            }
            guest++;
        }
        return guestsInformation.toString();
    }

    private String emergencyContactsInformation(List<EmergencyContactResponse> emergencyContactResponses) {
        StringBuilder emergencyContactsInformation = new StringBuilder("Contactos de emergencia:\n");
        int emergencyContact = 1;
        for (EmergencyContactResponse emergencyContactResponse : emergencyContactResponses) {
            emergencyContactsInformation.append("\tContacto de emergencia N°").append(emergencyContact).append("\n");
            emergencyContactsInformation.append("\t\tNombre completo: ").append(emergencyContactResponse.getFullName()).append("\n");
            emergencyContactsInformation.append("\t\tTeléfono de contacto: ").append(emergencyContactResponse.getContactPhone()).append("\n");
            emergencyContact++;
        }
        return emergencyContactsInformation.toString();
    }

}