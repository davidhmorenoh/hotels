package com.management.hotels.application.services;

import com.management.hotels.application.dtos.requests.HotelRequest;
import com.management.hotels.application.dtos.responses.HotelResponse;
import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.services.HotelDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelApplicationService {

    private final HotelDomainService hotelDomainService;

    private final GenericMapper<HotelRequest, HotelResponse, Hotel> hotelMapper;

    public List<HotelResponse> getAllHotels() {
        return hotelDomainService.getAllHotels().stream().map(hotelMapper::toDto).collect(Collectors.toList());
    }

    public HotelResponse getHotelById(Long id, Long userId) {
        Hotel hotel = hotelDomainService.validateUserOwnsHotel(id, userId);
        return hotelMapper.toDto(hotel);
    }

    public List<HotelResponse> getHotelsByUserId(Long userId) {
        return hotelDomainService.getHotelsByUserId(userId).stream().map(hotelMapper::toDto).collect(Collectors.toList());
    }

    public HotelResponse createHotel(HotelRequest hotelRequest) {
        Hotel hotel = hotelMapper.toEntity(hotelRequest);
        Hotel createdHotel = hotelDomainService.createHotel(hotel, hotelRequest.getUserId());
        return hotelMapper.toDto(createdHotel);
    }

    public HotelResponse updateHotel(Long id, HotelRequest hotelRequest) {
        Hotel hotelToUpdate = hotelMapper.toEntity(hotelRequest);
        Hotel updatedHotel = hotelDomainService.updateHotel(id, hotelToUpdate, hotelRequest.getUserId());
        return hotelMapper.toDto(updatedHotel);
    }

    public HotelResponse enableHotel(Long id, Long userId) {
        Hotel enabledHotel = hotelDomainService.enableHotel(id, userId);
        return hotelMapper.toDto(enabledHotel);
    }

    public HotelResponse disableHotel(Long id, Long userId) {
        Hotel disabledHotel = hotelDomainService.disableHotel(id, userId);
        return hotelMapper.toDto(disabledHotel);
    }

    public void deleteHotel(Long id, Long userId) {
        hotelDomainService.deleteHotel(id, userId);
    }

}