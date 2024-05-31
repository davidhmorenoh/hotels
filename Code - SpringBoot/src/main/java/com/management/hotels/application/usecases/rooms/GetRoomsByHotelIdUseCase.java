package com.management.hotels.application.usecases.rooms;

import com.management.hotels.application.dtos.RoomDto;
import com.management.hotels.application.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRoomsByHotelIdUseCase {

    private final RoomService roomService;

    public List<RoomDto> execute(Long hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }

}