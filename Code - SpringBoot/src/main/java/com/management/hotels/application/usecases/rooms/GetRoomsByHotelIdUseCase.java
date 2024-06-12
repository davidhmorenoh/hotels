package com.management.hotels.application.usecases.rooms;

import com.management.hotels.application.dtos.responses.RoomResponse;
import com.management.hotels.application.services.RoomApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRoomsByHotelIdUseCase {

    private final RoomApplicationService roomApplicationService;

    public List<RoomResponse> execute(Long hotelId, Long userId) {
        return roomApplicationService.getRoomsByHotelId(hotelId, userId);
    }

}