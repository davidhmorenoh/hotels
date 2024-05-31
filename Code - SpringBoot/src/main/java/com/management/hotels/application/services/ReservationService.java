package com.management.hotels.application.services;

import com.management.hotels.application.dtos.enums.StateDto;
import com.management.hotels.application.dtos.requests.ReservationRequest;
import com.management.hotels.application.dtos.responses.ReservationResponse;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.State;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.ports.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final GenericMapper<ReservationRequest, ReservationResponse, Reservation> reservationMapper;
    private final GenericMapper<StateDto, StateDto, State> stateMapper;

    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.toEntity(reservationRequest);
        return reservationMapper.toDto(reservationRepository.save(reservation));
    }

    public List<ReservationResponse> getReservationsByRoom(Long roomId) {
        Room room = new Room();
        room.setRoomId(roomId);
        return reservationRepository.findByRoom(room).stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReservationResponse> getReservationsByTraveler(Long travelerId) {
        User traveler = new User();
        traveler.setUserId(travelerId);
        return reservationRepository.findByTraveler(traveler).stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll().stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public ReservationResponse getReservationById(Long id) {
        return reservationMapper.toDto(reservationRepository.findById(id));
    }

    public ReservationResponse updateReservationStatus(Long reservationId, StateDto stateDto) {
        Reservation reservation = reservationRepository.findById(reservationId);
        reservation.setState(stateMapper.toEntity(stateDto));
        return reservationMapper.toDto(reservationRepository.save(reservation));
    }

    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id);
        reservationRepository.delete(reservation);
    }

}