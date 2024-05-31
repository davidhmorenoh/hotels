package com.management.hotels.application.services;

import com.management.hotels.application.dtos.HotelDto;
import com.management.hotels.application.dtos.UserDto;
import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.ports.repositories.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    private final GenericMapper<HotelDto, Hotel> hotelMapper;
    private final GenericMapper<StatusDto, Status> statusMapper;
    private final GenericMapper<UserDto, User> userMapper;

    private final UserService userService;

    public HotelDto createHotel(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        return hotelMapper.toDto(hotelRepository.save(hotel));
    }

    public HotelDto updateHotel(Long id, HotelDto hotelDto) {
        Hotel hotelToUpdate = hotelMapper.toEntity(hotelDto);

        Hotel hotelUpdated = hotelRepository.findById(id);

        hotelUpdated.setHotelName(hotelToUpdate.getHotelName());
        hotelUpdated.setCity(hotelToUpdate.getCity());
        hotelUpdated.setAddress(hotelToUpdate.getAddress());
        //hotelFound.setStatus(hotelToUpdate.getStatus());
        //hotelFound.setCreatedBy(hotelToUpdate.getCreatedBy());

        return hotelMapper.toDto(hotelRepository.save(hotelUpdated));
    }

    public List<HotelDto> getAllHotels() {
        return hotelRepository.findAll().stream().map(hotelMapper::toDto).collect(Collectors.toList());
    }

    public HotelDto getHotelById(Long id) {
        return hotelMapper.toDto(hotelRepository.findById(id));
    }

    public List<HotelDto> getHotelsByUserId(Long userId) {
        UserDto userDto = userService.getUserById(userId);
        return hotelRepository.findByCreatedBy(userMapper.toEntity(userDto)).stream().map(hotelMapper::toDto).collect(Collectors.toList());
    }

    public HotelDto updateHotelStatus(Long id, StatusDto statusDto) {
        Hotel hotel = hotelRepository.findById(id);
        hotel.setStatus(statusMapper.toEntity(statusDto));
        return hotelMapper.toDto(hotelRepository.save(hotel));
    }

    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id);
        hotelRepository.delete(hotel);
    }

}