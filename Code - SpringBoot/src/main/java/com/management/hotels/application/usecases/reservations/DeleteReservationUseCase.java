package com.management.hotels.application.usecases.reservations;

import com.management.hotels.application.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteReservationUseCase {

    private final ReservationService reservationService;

    public void execute(Long id, Long userId) {
        reservationService.deleteReservation(id, userId);
    }

}