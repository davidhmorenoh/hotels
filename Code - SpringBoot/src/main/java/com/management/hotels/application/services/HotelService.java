package com.management.hotels.application.services;

import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.dtos.requests.HotelRequest;
import com.management.hotels.application.dtos.responses.HotelResponse;
import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.ports.repositories.HotelRepository;
import com.management.hotels.domain.ports.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    private final GenericMapper<HotelRequest, HotelResponse, Hotel> hotelMapper;
    private final GenericMapper<StatusDto, StatusDto, Status> statusMapper;

    public HotelResponse createHotel(HotelRequest hotelRequest) {
        Hotel hotel = hotelMapper.toEntity(hotelRequest);
        return hotelMapper.toDto(hotelRepository.save(hotel));
    }

    public HotelResponse updateHotel(Long id, HotelRequest hotelRequest) {
        Status status = statusMapper.toEntity(hotelRequest.getStatus());

        User createdBy = userRepository.findById(hotelRequest.getCreatedBy());
        Hotel hotelUpdated = hotelRepository.findById(id);

        hotelUpdated.setHotelName(hotelRequest.getHotelName());
        hotelUpdated.setCity(hotelRequest.getCity());
        hotelUpdated.setAddress(hotelRequest.getAddress());
        hotelUpdated.setStatus(status);
        hotelUpdated.setCreatedBy(createdBy);

        return hotelMapper.toDto(hotelRepository.save(hotelUpdated));
    }

    public List<HotelResponse> getAllHotels() {
        return hotelRepository.findAll().stream().map(hotelMapper::toDto).collect(Collectors.toList());
    }

    public HotelResponse getHotelById(Long id) {
        return hotelMapper.toDto(hotelRepository.findById(id));
    }

    public List<HotelResponse> getHotelsByUserId(Long userId) {
        User user = userRepository.findById(userId);
        return hotelRepository.findByCreatedBy(user).stream().map(hotelMapper::toDto).collect(Collectors.toList());
    }

    public HotelResponse updateHotelStatus(Long id, StatusDto statusDto) {
        Hotel hotel = hotelRepository.findById(id);
        hotel.setStatus(statusMapper.toEntity(statusDto));
        return hotelMapper.toDto(hotelRepository.save(hotel));
    }

    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id);
        hotelRepository.delete(hotel);
    }

}