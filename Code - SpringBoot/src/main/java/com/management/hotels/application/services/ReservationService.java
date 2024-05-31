package com.management.hotels.application.services;

import com.management.hotels.application.dtos.ReservationDto;
import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.Status;
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

    private final GenericMapper<ReservationDto, Reservation> reservationMapper;
    private final GenericMapper<StatusDto, Status> statusMapper;

    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.toEntity(reservationDto);
        return reservationMapper.toDto(reservationRepository.save(reservation));
    }

    public List<ReservationDto> getReservationsByRoom(Long roomId) {
        Room room = new Room();
        room.setRoomId(roomId);
        return reservationRepository.findByRoom(room).stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReservationDto> getReservationsByTraveler(Long travelerId) {
        User traveler = new User();
        traveler.setUserId(travelerId);
        return reservationRepository.findByTraveler(traveler).stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public ReservationDto getReservationById(Long id) {
        return reservationMapper.toDto(reservationRepository.findById(id));
    }

    public ReservationDto updateReservationStatus(Long reservationId, StatusDto statusDto) {
        Reservation reservation = reservationRepository.findById(reservationId);
        reservation.setStatus(statusMapper.toEntity(statusDto));
        return reservationMapper.toDto(reservationRepository.save(reservation));
    }

    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id);
        reservationRepository.delete(reservation);
    }

}