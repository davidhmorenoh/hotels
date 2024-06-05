package com.management.hotels.application.services;

import com.management.hotels.application.dtos.enums.StateDto;
import com.management.hotels.application.dtos.requests.EmergencyContactRequest;
import com.management.hotels.application.dtos.requests.GuestRequest;
import com.management.hotels.application.dtos.requests.ReservationRequest;
import com.management.hotels.application.dtos.responses.EmergencyContactResponse;
import com.management.hotels.application.dtos.responses.GuestResponse;
import com.management.hotels.application.dtos.responses.ReservationResponse;
import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.State;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.entities.enums.UserType;
import com.management.hotels.domain.exceptions.reservations.BookingPreconditionsException;
import com.management.hotels.domain.exceptions.reservations.ReservationAlreadyCancelledException;
import com.management.hotels.domain.exceptions.users.UserNotAuthorizedToPerformOperationException;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.ports.repositories.ReservationRepository;
import com.management.hotels.domain.ports.repositories.RoomRepository;
import com.management.hotels.domain.ports.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    private final EmergencyContactService emergencyContactService;
    private final GuestService guestService;
    private final EmailService emailService;
    private final RoomService roomService;
    private final HotelService hotelService;

    private final GenericMapper<ReservationRequest, ReservationResponse, Reservation> reservationMapper;
    private final GenericMapper<StateDto, StateDto, State> stateMapper;

    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll().stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReservationResponse> getReservationsByHotelId(Long hotelId, Long userId) {
        Hotel hotel = hotelService.validateAgentHotels(userId, hotelId);
        return reservationRepository.findByHotel(hotel).stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReservationResponse> getReservationsByRoomId(Long roomId, Long userId) {
        Room room = roomService.validateAgentRooms(userId, roomId);
        return reservationRepository.findByRoom(room).stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReservationResponse> getReservationsByTraveler(Long travelerId) {
        User traveler = this.validateUserTypeTraveler(travelerId);
        return reservationRepository.findByUser(traveler).stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public ReservationResponse getReservationById(Long id, Long userId) {
        Reservation reservation = this.validateTravelerReservations(userId, id);
        return reservationMapper.toDto(reservation);
    }

    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        if (Objects.isNull(reservationRequest.getState())) {
            reservationRequest.setState(StateDto.Confirmed);
        }

        reservationRequest.setNumberOfGuests(reservationRequest.getGuests().size());
        reservationRequest.setReservationDate(new Timestamp(System.currentTimeMillis()));

        List<EmergencyContactRequest> emergencyContacts = reservationRequest.getEmergencyContacts();
        reservationRequest.setEmergencyContacts(null);

        List<GuestRequest> guests = reservationRequest.getGuests();
        reservationRequest.setGuests(null);

        Reservation reservation = reservationMapper.toEntity(reservationRequest);

        User user = this.validateUserTypeTraveler(reservationRequest.getUserId());
        reservation.setUser(user);

        Room room = roomRepository.findById(reservationRequest.getRoomId());
        reservation.setRoom(room);

        if (reservation.getNumberOfGuests() > room.getCapacity()) {
            throw new BookingPreconditionsException("The number of guests exceeds the capacity of the room.");
        }

        if (room.getStatus().equals(Status.Disabled)) {
            throw new BookingPreconditionsException("The room is not available for booking.");
        }

        if (reservation.getCheckInDate().after(reservation.getCheckOutDate())) {
            throw new BookingPreconditionsException("The check out date must be after check in date.");
        }

        if (reservation.getCheckInDate().equals(reservation.getCheckOutDate())) {
            throw new BookingPreconditionsException("No days for the reservation, at least one day must be reserved.");
        }

        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(room.getRoomId(), reservation.getCheckInDate(), reservation.getCheckOutDate());

        if (!conflictingReservations.isEmpty()) {
            throw new BookingPreconditionsException("Room is already reserved for the selected dates.");
        }

        Reservation reservationDone = reservationRepository.save(reservation);
        ReservationResponse reservationResponse = reservationMapper.toDto(reservationDone);

        List<EmergencyContactResponse> emergencyContactResponses = emergencyContactService.createEmergencyContacts(emergencyContacts, reservationDone);
        List<GuestResponse> guestResponses = guestService.createGuest(guests, reservationDone);

        reservationResponse.setEmergencyContacts(emergencyContactResponses);
        reservationResponse.setGuests(guestResponses);

        emailService.sendNotificationForNewBooking(user.getEmail(), reservationResponse);

        return reservationResponse;
    }

    public ReservationResponse cancelReservation(Long id, Long userId) {
        Reservation reservation = this.validateTravelerReservations(userId, id);
        if (reservation.getState().equals(State.Cancelled)) {
            throw new ReservationAlreadyCancelledException("No updated, because reservation already is cancelled");
        }
        reservation.setState(State.Cancelled);
        ReservationResponse reservationResponse = reservationMapper.toDto(reservationRepository.save(reservation));
        emailService.sendNotificationForBookingUpdated(reservation.getUser().getEmail(), reservationResponse);
        return reservationResponse;
    }

    public void deleteReservation(Long id, Long userId) {
        Reservation reservation = this.validateTravelerReservations(userId, id);
        reservationRepository.delete(reservation);
    }

    private User validateUserTypeTraveler(Long userId) {
        User user = userRepository.findById(userId);
        if (!user.getUserType().equals(UserType.Traveler)) {
            throw new UserNotAuthorizedToPerformOperationException("An traveler can be perform this operation only");
        }
        return user;
    }

    private Reservation validateTravelerReservations(Long userId, Long reservationId) {
        User user = this.validateUserTypeTraveler(userId);
        Reservation reservation = reservationRepository.findById(reservationId);
        if (!reservation.getUser().equals(user)) {
            throw new UserNotAuthorizedToPerformOperationException("This traveler can't see, modified or deleted this reservation");
        }
        return reservation;
    }

}