package com.management.hotels.infrastructure.adapters.web;

import com.management.hotels.application.dtos.RoomDto;
import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.usecases.rooms.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final CreateRoomUseCase createRoomUseCase;
    private final GetRoomByIdUseCase getRoomByIdUseCase;
    private final GetRoomsByHotelIdUseCase getRoomsByHotelIdUseCase;
    private final GetAllRoomsUseCase getAllRoomsUseCase;
    private final UpdateRoomStatusUseCase updateRoomStatusUseCase;
    private final UpdateRoomUseCase updateRoomUseCase;
    private final EnableRoomUseCase enableRoomUseCase;
    private final DeleteRoomUseCase deleteRoomUseCase;

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        return ResponseEntity.ok(getAllRoomsUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(getRoomByIdUseCase.execute(id));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomDto>> getRoomsByHotelId(@PathVariable Long hotelId) {
        return ResponseEntity.ok(getRoomsByHotelIdUseCase.execute(hotelId));
    }

    @PostMapping("/create")
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(createRoomUseCase.execute(roomDto));
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<RoomDto> enableRoom(@PathVariable Long roomId, @RequestParam StatusDto statusDto) {
        return ResponseEntity.ok(updateRoomStatusUseCase.execute(roomId, statusDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable Long id, @RequestBody RoomDto roomDetails) {
        return ResponseEntity.ok(updateRoomUseCase.execute(id, roomDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        deleteRoomUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}