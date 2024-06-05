package com.management.hotels.application.usecases.reservations;

import com.management.hotels.application.dtos.requests.ReservationRequest;
import com.management.hotels.application.dtos.responses.ReservationResponse;
import com.management.hotels.application.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateReservationUseCase {

    private final ReservationService reservationService;

    public ReservationResponse execute(ReservationRequest reservationRequest) {
        return reservationService.createReservation(reservationRequest);
    }

}