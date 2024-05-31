package com.management.hotels.application.usecases.rooms;

import com.management.hotels.application.dtos.RoomDto;
import com.management.hotels.application.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllRoomsUseCase {

    private final RoomService roomService;

    public List<RoomDto> execute() {
        return roomService.getAllRooms();
    }

}