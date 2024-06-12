package com.management.hotels.application.usecases.reservations;

import com.management.hotels.application.dtos.responses.ReservationResponse;
import com.management.hotels.application.services.ReservationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetReservationByIdUseCase {

    private final ReservationApplicationService reservationApplicationService;

    public ReservationResponse execute(Long id, Long userId) {
        return reservationApplicationService.getReservationById(id, userId);
    }

}