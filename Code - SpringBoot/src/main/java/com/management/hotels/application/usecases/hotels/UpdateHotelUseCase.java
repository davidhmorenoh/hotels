package com.management.hotels.application.usecases.hotels;

import com.management.hotels.application.dtos.requests.HotelRequest;
import com.management.hotels.application.dtos.responses.HotelResponse;
import com.management.hotels.application.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateHotelUseCase {

    private final HotelService hotelService;

    public HotelResponse execute(Long id, HotelRequest hotelRequest) {
        return hotelService.updateHotel(id, hotelRequest);
    }

}