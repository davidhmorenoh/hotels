package com.management.hotels.application.controllers;

import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @GetMapping("/hotel/{hotelId}")
    public List<Room> getRoomsByHotelId(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }

    @PostMapping("/create")
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @PatchMapping("/{id}/enable")
    public Room enableRoom(@PathVariable Long roomId, @RequestParam Status status) {
        return roomService.updateRoomStatus(roomId, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        return ResponseEntity.ok(roomService.updateRoom(id, roomDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

}