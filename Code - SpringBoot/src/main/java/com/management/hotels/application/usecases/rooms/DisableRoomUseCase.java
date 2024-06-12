package com.management.hotels.application.usecases.rooms;

import com.management.hotels.application.dtos.responses.RoomResponse;
import com.management.hotels.application.services.RoomApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisableRoomUseCase {

    private final RoomApplicationService roomApplicationService;

    public RoomResponse execute(Long id, Long userId) {
        return roomApplicationService.disableRoom(id, userId);
    }

}