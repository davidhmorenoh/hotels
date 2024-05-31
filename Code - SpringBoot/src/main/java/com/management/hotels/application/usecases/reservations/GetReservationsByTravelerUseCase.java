package com.management.hotels.application.usecases.reservations;

import com.management.hotels.application.dtos.ReservationDto;
import com.management.hotels.application.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetReservationsByTravelerUseCase {

    private final ReservationService reservationService;

    public List<ReservationDto> execute(Long travelerId) {
        return reservationService.getReservationsByTraveler(travelerId);
    }

}