package com.management.hotels.domain.services;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.exceptions.hotels.HotelAlreadyDisabledException;
import com.management.hotels.domain.exceptions.hotels.HotelAlreadyEnabledException;
import com.management.hotels.domain.exceptions.users.UserNotAuthorizedToPerformOperationException;
import com.management.hotels.domain.ports.repositories.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelDomainService {

    private final HotelRepository hotelRepository;

    private final UserDomainService userDomainService;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    public List<Hotel> getHotelsByUserId(Long userId) {
        User user = userDomainService.validateUserIsAgent(userId);
        return hotelRepository.findByUser(user);
    }

    public Hotel createHotel(Hotel hotel, Long userId) {
        User user = userDomainService.validateUserIsAgent(userId);
        hotel.setUser(user);
        if (hotel.getStatus() == null) {
            hotel.setStatus(Status.Enabled);
        }
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, Hotel updatedHotel, Long userId) {
        Hotel existingHotel = this.validateUserOwnsHotel(id, userId);
        existingHotel.setHotelName(updatedHotel.getHotelName());
        existingHotel.setCity(updatedHotel.getCity());
        existingHotel.setAddress(updatedHotel.getAddress());
        existingHotel.setStatus(updatedHotel.getStatus());
        return hotelRepository.save(existingHotel);
    }

    public Hotel enableHotel(Long id, Long userId) {
        Hotel hotel = this.validateUserOwnsHotel(id, userId);
        if (hotel.getStatus() == Status.Enabled) {
            throw new HotelAlreadyEnabledException("The hotel is already enabled.");
        }
        hotel.setStatus(Status.Enabled);
        return hotelRepository.save(hotel);
    }

    public Hotel disableHotel(Long id, Long userId) {
        Hotel hotel = this.validateUserOwnsHotel(id, userId);
        if (hotel.getStatus() == Status.Disabled) {
            throw new HotelAlreadyDisabledException("The hotel is already disabled.");
        }
        hotel.setStatus(Status.Disabled);
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id, Long userId) {
        Hotel hotel = this.validateUserOwnsHotel(id, userId);
        hotelRepository.delete(hotel);
    }

    public Hotel validateUserOwnsHotel(Long id, Long userId) {
        User user = userDomainService.validateUserIsAgent(userId);
        Hotel hotel = this.getHotelById(id);
        if (hotel.getUser() != user) {
            throw new UserNotAuthorizedToPerformOperationException("This agent does not have permission to manage this hotel.");
        }
        return hotel;
    }

}