package com.management.hotels.application.usecases.reservations;

import com.management.hotels.application.services.ReservationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteReservationUseCase {

    private final ReservationApplicationService reservationApplicationService;

    public void execute(Long id, Long userId) {
        reservationApplicationService.deleteReservation(id, userId);
    }

}