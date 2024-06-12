package com.management.hotels.domain.services;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.State;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.exceptions.reservations.BookingPreconditionsException;
import com.management.hotels.domain.exceptions.reservations.ReservationAlreadyCancelledException;
import com.management.hotels.domain.exceptions.users.UserNotAuthorizedToPerformOperationException;
import com.management.hotels.domain.ports.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReservationDomainService {

    private final ReservationRepository reservationRepository;

    private final HotelDomainService hotelDomainService;
    private final UserDomainService userDomainService;
    private final RoomDomainService roomDomainService;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByHotelId(Long hotelId, Long userId) {
        Hotel hotel = hotelDomainService.validateUserOwnsHotel(hotelId, userId);
        return reservationRepository.findByHotel(hotel);
    }

    public List<Reservation> getReservationsByRoomId(Long roomId, Long userId) {
        Room room = roomDomainService.validateUserOwnsRoom(roomId, userId);
        return reservationRepository.findByRoom(room);
    }

    public List<Reservation> getReservationsByTraveler(Long travelerId) {
        User traveler = userDomainService.validateUserIsTraveler(travelerId);
        return reservationRepository.findByUser(traveler);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation, Long userId, Long roomId) {
        if (Objects.isNull(reservation.getState())) {
            reservation.setState(State.Confirmed);
        }

        User user = userDomainService.validateUserIsTraveler(userId);
        reservation.setUser(user);

        reservation.setReservationDate(new Timestamp(System.currentTimeMillis()));

        Room room = roomDomainService.getRoomById(roomId);
        reservation.setRoom(room);

        if (reservation.getNumberOfGuests() > room.getCapacity()) {
            throw new BookingPreconditionsException("The number of guests exceeds the capacity of the room.");
        }

        if (room.getStatus() == Status.Disabled) {
            throw new BookingPreconditionsException("The room is not available for booking.");
        }

        if (reservation.getCheckInDate().after(reservation.getCheckOutDate())) {
            throw new BookingPreconditionsException("The check-out date must be after the check-in date.");
        }

        if (reservation.getCheckInDate().equals(reservation.getCheckOutDate())) {
            throw new BookingPreconditionsException("At least one day must be reserved.");
        }

        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(room.getRoomId(), reservation.getCheckInDate(), reservation.getCheckOutDate());

        if (!conflictingReservations.isEmpty()) {
            throw new BookingPreconditionsException("Room is already reserved for the selected dates.");
        }

        return reservationRepository.save(reservation);
    }

    public Reservation cancelReservation(Long id, Long userId) {
        Reservation reservation = this.validateTravelerOwnsReservations(id, userId);
        if (reservation.getState() == State.Cancelled) {
            throw new ReservationAlreadyCancelledException("The reservation is already cancelled.");
        }
        reservation.setState(State.Cancelled);
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id, Long userId) {
        Reservation reservation = this.validateTravelerOwnsReservations(id, userId);
        reservationRepository.delete(reservation);
    }

    public Reservation validateTravelerOwnsReservations(Long id, Long userId) {
        User user = userDomainService.validateUserIsTraveler(userId);
        Reservation reservation = this.getReservationById(id);
        if (reservation.getUser() != user) {
            throw new UserNotAuthorizedToPerformOperationException("This traveler does not have permission to manage this reservation.");
        }
        return reservation;
    }

}