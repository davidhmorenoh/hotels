package com.management.hotels.application.usecases.hotels;

import com.management.hotels.application.services.HotelApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteHotelUseCase {

    private final HotelApplicationService hotelApplicationService;

    public void execute(Long id, Long userId) {
        hotelApplicationService.deleteHotel(id, userId);
    }

}