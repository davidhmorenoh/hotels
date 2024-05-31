package com.management.hotels.application.usecases.hotels;

import com.management.hotels.application.dtos.HotelDto;
import com.management.hotels.application.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllHotelsUseCase {

    private final HotelService hotelService;

    public List<HotelDto> execute() {
        return hotelService.getAllHotels();
    }

}