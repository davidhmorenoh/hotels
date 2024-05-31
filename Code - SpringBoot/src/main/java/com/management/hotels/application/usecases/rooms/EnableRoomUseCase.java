package com.management.hotels.application.usecases.rooms;

import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.dtos.responses.RoomResponse;
import com.management.hotels.application.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnableRoomUseCase {

    private final RoomService roomService;

    public RoomResponse execute(Long id, StatusDto enabled) {
        return roomService.enableRoom(id, enabled);
    }

}