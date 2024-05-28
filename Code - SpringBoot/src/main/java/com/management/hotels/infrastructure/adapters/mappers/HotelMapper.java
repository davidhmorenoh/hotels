package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.HotelDTO;
import com.management.hotels.domain.entities.Hotel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper extends GenericMapper<HotelDTO, Hotel> {

    public HotelMapper(ModelMapper modelMapper) {
        super(modelMapper, HotelDTO.class, Hotel.class);
    }

}