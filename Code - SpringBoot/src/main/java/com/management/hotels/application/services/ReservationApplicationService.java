package com.management.hotels.application.services;

import com.management.hotels.application.dtos.requests.EmergencyContactRequest;
import com.management.hotels.application.dtos.requests.GuestRequest;
import com.management.hotels.application.dtos.requests.ReservationRequest;
import com.management.hotels.application.dtos.responses.EmergencyContactResponse;
import com.management.hotels.application.dtos.responses.GuestResponse;
import com.management.hotels.application.dtos.responses.ReservationResponse;
import com.management.hotels.domain.entities.EmergencyContact;
import com.management.hotels.domain.entities.Guest;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.ports.services.EmergencyContactDomainPortService;
import com.management.hotels.domain.ports.services.GuestDomainPortService;
import com.management.hotels.domain.ports.services.email.EmailDomainPortService;
import com.management.hotels.domain.services.ReservationDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationApplicationService {

    private final ReservationDomainService reservationDomainService;

    private final EmailDomainPortService emailDomainPortService;
    private final EmergencyContactDomainPortService emergencyContactDomainPortService;
    private final GuestDomainPortService guestDomainPortService;

    private final EmergencyContactApplicationService emergencyContactApplicationService;
    private final GuestApplicationService guestApplicationService;

    private final GenericMapper<ReservationRequest, ReservationResponse, Reservation> reservationMapper;

    public List<ReservationResponse> getAllReservations() {
        return reservationDomainService.getAllReservations().stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReservationResponse> getReservationsByHotelId(Long hotelId, Long userId) {
        return reservationDomainService.getReservationsByHotelId(hotelId, userId).stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReservationResponse> getReservationsByRoomId(Long roomId, Long userId) {
        return reservationDomainService.getReservationsByRoomId(roomId, userId).stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReservationResponse> getReservationsByTraveler(Long travelerId) {
        return reservationDomainService.getReservationsByTraveler(travelerId).stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public ReservationResponse getReservationById(Long id, Long userId) {
        Reservation reservation = reservationDomainService.validateTravelerOwnsReservations(id, userId);
        return reservationMapper.toDto(reservation);
    }

    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        List<EmergencyContactRequest> emergencyContactRequests = reservationRequest.getEmergencyContacts();
        reservationRequest.setEmergencyContacts(null);

        List<GuestRequest> guestRequests = reservationRequest.getGuests();
        reservationRequest.setGuests(null);

        Reservation reservation = reservationMapper.toEntity(reservationRequest);

        reservation.setNumberOfGuests(guestRequests.size());

        Reservation reservationDone = reservationDomainService.createReservation(reservation, reservationRequest.getUserId(), reservationRequest.getRoomId());
        ReservationResponse reservationResponse = reservationMapper.toDto(reservationDone);

        List<EmergencyContactResponse> emergencyContactResponses = emergencyContactApplicationService.createEmergencyContacts(emergencyContactRequests, reservationDone);
        List<GuestResponse> guestResponses = guestApplicationService.createGuest(guestRequests, reservationDone);

        reservationResponse.setEmergencyContacts(emergencyContactResponses);
        reservationResponse.setGuests(guestResponses);

        List<EmergencyContact> emergencyContacts = emergencyContactDomainPortService.getEmergencyContactsByReservation(reservationDone);
        List<Guest> guests = guestDomainPortService.getGuestsByReservation(reservationDone);

        reservationDone.setEmergencyContacts(emergencyContacts);
        reservationDone.setGuests(guests);

        emailDomainPortService.sendNotificationForNewBooking(reservationDone);

        return reservationResponse;
    }

    public ReservationResponse cancelReservation(Long id, Long userId) {
        Reservation reservation = reservationDomainService.cancelReservation(id, userId);
        emailDomainPortService.sendNotificationForBookingUpdated(reservation);
        return reservationMapper.toDto(reservation);
    }

    public void deleteReservation(Long id, Long userId) {
        reservationDomainService.deleteReservation(id, userId);
    }

}