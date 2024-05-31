package com.management.hotels.application.usecases.hotels;

import com.management.hotels.application.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteHotelUseCase {

    private final HotelService hotelService;

    public void execute(Long id) {
        hotelService.deleteHotel(id);
    }

}