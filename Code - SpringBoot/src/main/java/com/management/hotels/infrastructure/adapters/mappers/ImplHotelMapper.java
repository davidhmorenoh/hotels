package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.requests.HotelRequest;
import com.management.hotels.application.dtos.responses.HotelResponse;
import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplHotelMapper implements GenericMapper<HotelRequest, HotelResponse, Hotel> {

    private final ModelMapper modelMapper;

    @Override
    public HotelResponse toDto(Hotel hotel) {
        return modelMapper.map(hotel, HotelResponse.class);
    }

    @Override
    public Hotel toEntity(HotelRequest hotelRequest) {
        return modelMapper.map(hotelRequest, Hotel.class);
    }

}