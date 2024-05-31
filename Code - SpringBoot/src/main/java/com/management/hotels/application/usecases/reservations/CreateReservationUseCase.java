package com.management.hotels.application.usecases.reservations;

import com.management.hotels.application.dtos.ReservationDto;
import com.management.hotels.application.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateReservationUseCase {

    private final ReservationService reservationService;

    public ReservationDto execute(ReservationDto reservationDto) {
        return reservationService.createReservation(reservationDto);
    }

}