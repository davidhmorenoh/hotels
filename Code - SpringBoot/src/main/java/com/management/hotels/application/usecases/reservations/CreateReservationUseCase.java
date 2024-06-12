package com.management.hotels.application.usecases.reservations;

import com.management.hotels.application.dtos.requests.ReservationRequest;
import com.management.hotels.application.dtos.responses.ReservationResponse;
import com.management.hotels.application.services.ReservationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateReservationUseCase {

    private final ReservationApplicationService reservationApplicationService;

    public ReservationResponse execute(ReservationRequest reservationRequest) {
        return reservationApplicationService.createReservation(reservationRequest);
    }

}