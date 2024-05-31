package com.management.hotels.application.usecases.hotels;

import com.management.hotels.application.dtos.HotelDto;
import com.management.hotels.application.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateHotelUseCase {

    private final HotelService hotelService;

    public HotelDto execute(HotelDto hotelDto) {
        return hotelService.
                createHotel(hotelDto);
    }

}