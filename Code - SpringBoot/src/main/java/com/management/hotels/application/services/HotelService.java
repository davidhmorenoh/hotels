package com.management.hotels.application.services;

import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.dtos.requests.HotelRequest;
import com.management.hotels.application.dtos.responses.HotelResponse;
import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.entities.enums.UserType;
import com.management.hotels.domain.exceptions.hotels.HotelAlreadyDisabledException;
import com.management.hotels.domain.exceptions.hotels.HotelAlreadyEnabledException;
import com.management.hotels.domain.exceptions.users.UserNotAuthorizedToPerformOperationException;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import com.management.hotels.domain.ports.repositories.HotelRepository;
import com.management.hotels.domain.ports.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    private final GenericMapper<HotelRequest, HotelResponse, Hotel> hotelMapper;

    public List<HotelResponse> getAllHotels() {
        return hotelRepository.findAll().stream().map(hotelMapper::toDto).collect(Collectors.toList());
    }

    public HotelResponse getHotelById(Long id, Long userId) {
        Hotel hotel = this.validateAgentHotels(userId, id);
        return hotelMapper.toDto(hotel);
    }

    public List<HotelResponse> getHotelsByUserId(Long userId) {
        User user = this.validateUserTypeAgent(userId);
        return hotelRepository.findByCreatedBy(user).stream().map(hotelMapper::toDto).collect(Collectors.toList());
    }

    public HotelResponse createHotel(HotelRequest hotelRequest) {
        if (Objects.isNull(hotelRequest.getStatus())) {
            hotelRequest.setStatus(StatusDto.Enabled);
        }

        Hotel hotel = hotelMapper.toEntity(hotelRequest);

        User createdBy = this.validateUserTypeAgent(hotelRequest.getCreatedBy());
        hotel.setCreatedBy(createdBy);

        return hotelMapper.toDto(hotelRepository.save(hotel));
    }

    public HotelResponse updateHotel(Long id, HotelRequest hotelRequest) {
        Hotel hotelUpdated = this.validateAgentHotels(hotelRequest.getCreatedBy(), id);

        Hotel hotelToUpdate = hotelMapper.toEntity(hotelRequest);

        User user = userRepository.findById(hotelRequest.getCreatedBy());

        hotelUpdated.setHotelName(hotelToUpdate.getHotelName());
        hotelUpdated.setCity(hotelToUpdate.getCity());
        hotelUpdated.setAddress(hotelToUpdate.getAddress());
        hotelUpdated.setStatus(hotelToUpdate.getStatus());
        hotelUpdated.setCreatedBy(user);

        return hotelMapper.toDto(hotelRepository.save(hotelUpdated));
    }

    public HotelResponse enableHotel(Long id, Long userId) {
        Hotel hotel = this.validateAgentHotels(userId, id);
        if (hotel.getStatus().equals(Status.Enabled)) {
            throw new HotelAlreadyEnabledException("No updated, because hotel already is enable");
        }
        hotel.setStatus(Status.Enabled);
        return hotelMapper.toDto(hotelRepository.save(hotel));
    }

    public HotelResponse disableHotel(Long id, Long userId) {
        Hotel hotel = this.validateAgentHotels(userId, id);
        if (hotel.getStatus().equals(Status.Disabled)) {
            throw new HotelAlreadyDisabledException("No updated, because hotel already is disable");
        }
        hotel.setStatus(Status.Disabled);
        return hotelMapper.toDto(hotelRepository.save(hotel));
    }

    public void deleteHotel(Long id, Long userId) {
        Hotel hotel = this.validateAgentHotels(userId, id);
        hotelRepository.delete(hotel);
    }

    public User validateUserTypeAgent(Long userId) {
        User user = userRepository.findById(userId);
        if (!user.getUserType().equals(UserType.Agent)) {
            throw new UserNotAuthorizedToPerformOperationException("An agent can be perform this operation only");
        }
        return user;
    }

    public Hotel validateAgentHotels(Long userId, Long hotelId) {
        User user = this.validateUserTypeAgent(userId);
        Hotel hotel = hotelRepository.findById(hotelId);
        if (!hotel.getCreatedBy().equals(user)) {
            throw new UserNotAuthorizedToPerformOperationException("This agent can't see, modified or deleted this hotel");
        }
        return hotel;
    }

}