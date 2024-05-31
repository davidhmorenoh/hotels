package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.HotelDto;
import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplHotelMapper implements GenericMapper<HotelDto, Hotel> {

    private final ModelMapper modelMapper;

    @Override
    public HotelDto toDto(Hotel hotel) {
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public Hotel toEntity(HotelDto hotelDto) {
        return modelMapper.map(hotelDto, Hotel.class);
    }

}