package com.management.hotels.application.usecases.hotels;

import com.management.hotels.application.dtos.requests.HotelRequest;
import com.management.hotels.application.dtos.responses.HotelResponse;
import com.management.hotels.application.services.HotelApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateHotelUseCase {

    private final HotelApplicationService hotelApplicationService;

    public HotelResponse execute(HotelRequest hotelRequest) {
        return hotelApplicationService.
                createHotel(hotelRequest);
    }

}