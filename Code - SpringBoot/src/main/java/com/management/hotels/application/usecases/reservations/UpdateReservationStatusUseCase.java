package com.management.hotels.application.usecases.reservations;

import com.management.hotels.application.dtos.enums.StateDto;
import com.management.hotels.application.dtos.responses.ReservationResponse;
import com.management.hotels.application.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateReservationStatusUseCase {

    private final ReservationService reservationService;

    public ReservationResponse execute(Long reservationId, StateDto stateDto) {
        return reservationService.updateReservationStatus(reservationId, stateDto);
    }

}