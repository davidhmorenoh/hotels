package com.management.hotels.application.usecases.reservations;

import com.management.hotels.application.dtos.responses.ReservationResponse;
import com.management.hotels.application.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetReservationByIdUseCase {

    private final ReservationService reservationService;

    public ReservationResponse execute(Long id) {
        return reservationService.getReservationById(id);
    }

}