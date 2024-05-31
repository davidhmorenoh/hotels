package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.requests.ReservationRequest;
import com.management.hotels.application.dtos.responses.ReservationResponse;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplReservationMapper implements GenericMapper<ReservationRequest, ReservationResponse, Reservation> {

    private final ModelMapper modelMapper;

    @Override
    public ReservationResponse toDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationResponse.class);
    }

    @Override
    public Reservation toEntity(ReservationRequest reservationRequest) {
        return modelMapper.map(reservationRequest, Reservation.class);
    }

}