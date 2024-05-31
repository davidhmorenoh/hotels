package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.ReservationDto;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplReservationMapper implements GenericMapper<ReservationDto, Reservation> {

    private final ModelMapper modelMapper;

    @Override
    public ReservationDto toDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }

    @Override
    public Reservation toEntity(ReservationDto reservationDto) {
        return modelMapper.map(reservationDto, Reservation.class);
    }

}