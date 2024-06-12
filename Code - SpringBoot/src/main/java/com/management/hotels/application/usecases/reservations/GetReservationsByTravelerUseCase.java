package com.management.hotels.application.usecases.reservations;

import com.management.hotels.application.dtos.responses.ReservationResponse;
import com.management.hotels.application.services.ReservationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetReservationsByTravelerUseCase {

    private final ReservationApplicationService reservationApplicationService;

    public List<ReservationResponse> execute(Long userId) {
        return reservationApplicationService.getReservationsByTraveler(userId);
    }

}