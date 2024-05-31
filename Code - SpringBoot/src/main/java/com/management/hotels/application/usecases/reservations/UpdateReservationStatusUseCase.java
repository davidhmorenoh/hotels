package com.management.hotels.application.usecases.reservations;

import com.management.hotels.application.dtos.ReservationDto;
import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateReservationStatusUseCase {

    private final ReservationService reservationService;

    public ReservationDto execute(Long reservationId, StatusDto statusDto) {
        return reservationService.updateReservationStatus(reservationId, statusDto);
    }

}