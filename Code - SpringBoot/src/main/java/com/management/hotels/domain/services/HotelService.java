package com.management.hotels.domain.services;

import com.management.hotels.application.dtos.HotelDTO;
import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.infrastructure.adapters.mappers.HotelMapper;
import com.management.hotels.infrastructure.adapters.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelDTO createHotel(HotelDTO hotelDTO) {
        Hotel hotel = hotelMapper.toEntity(hotelDTO);
        hotel = hotelRepository.save(hotel);
        return hotelMapper.toDTO(hotel);
    }

    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) {
        return Optional.of(hotelRepository.findById(id).map(hotel -> {

            Hotel hotelToUpdate = hotelMapper.toEntity(hotelDTO);
            hotel.setHotelName(hotelToUpdate.getHotelName());
            hotel.setCity(hotelToUpdate.getCity());
            hotel.setAddress(hotelToUpdate.getAddress());
            //hotel.setStatus(hotelToUpdate.getStatus());
            //hotel.setCreatedBy(hotelToUpdate.getCreatedBy());

            Hotel updatedHotel = hotelRepository.save(hotel);
            return hotelMapper.toDTO(updatedHotel);
        })).get().orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::toDTO)
                .collect(Collectors.toList());
    }

    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
        return hotelMapper.toDTO(hotel);
    }

    public List<Hotel> getHotelsByUser(User user) {
        return hotelRepository.findByCreatedBy(user);
    }

    public HotelDTO updateHotelStatus(Long id, Status status) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
        hotel.setStatus(status);
        hotel = hotelRepository.save(hotel);
        return hotelMapper.toDTO(hotel);
    }

    public void enableHotel(Long id, Status enabled) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            Hotel h = hotel.get();
            h.setStatus(enabled);
            hotelRepository.save(h);
        }
    }

    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
        hotelRepository.delete(hotel);
    }

}