package com.management.hotels.application.usecases.reservations;

import com.management.hotels.application.dtos.responses.ReservationResponse;
import com.management.hotels.application.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetReservationsByRoomIdUseCase {

    private final ReservationService reservationService;

    public List<ReservationResponse> execute(Long roomId, Long userId) {
        return reservationService.getReservationsByRoomId(roomId, userId);
    }

}