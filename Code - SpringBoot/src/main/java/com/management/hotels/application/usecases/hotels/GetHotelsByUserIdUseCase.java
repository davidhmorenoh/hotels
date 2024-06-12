package com.management.hotels.application.usecases.hotels;

import com.management.hotels.application.dtos.responses.HotelResponse;
import com.management.hotels.application.services.HotelApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetHotelsByUserIdUseCase {

    private final HotelApplicationService hotelApplicationService;

    public List<HotelResponse> execute(Long id) {
        return hotelApplicationService.getHotelsByUserId(id);
    }

}