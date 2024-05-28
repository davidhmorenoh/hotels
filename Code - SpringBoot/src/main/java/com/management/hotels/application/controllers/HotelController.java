package com.management.hotels.application.controllers;

import com.management.hotels.application.dtos.HotelDTO;
import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.services.HotelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@AllArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/")
    public List<HotelDTO> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public HotelDTO getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Hotel> getHotelsByUser(@PathVariable Long userId) {
        User user = new User();
        user.setUserId(userId);
        return hotelService.getHotelsByUser(user);
    }

    @PostMapping("/create")
    public ResponseEntity<HotelDTO> createHotel(@Valid @RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelService.createHotel(hotelDTO));
    }

    @PutMapping("/{id}")
    public HotelDTO updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        return hotelService.updateHotel(id, hotelDTO);
    }

    @PatchMapping("/{id}/enable")
    public HotelDTO enableHotel(@PathVariable Long id, @RequestParam Status status) {
        return hotelService.updateHotelStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

}