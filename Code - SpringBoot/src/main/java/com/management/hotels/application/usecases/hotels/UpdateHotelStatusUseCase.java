package com.management.hotels.application.usecases.hotels;

import com.management.hotels.application.dtos.HotelDto;
import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateHotelStatusUseCase {

    private final HotelService hotelService;

    public HotelDto execute(Long id, StatusDto statusDto) {
        return hotelService.updateHotelStatus(id, statusDto);
    }

}