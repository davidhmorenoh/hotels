package com.management.hotels.infrastructure.adapters.web;

import com.management.hotels.application.dtos.HotelDto;
import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.usecases.hotels.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final CreateHotelUseCase createHotelUseCase;
    private final UpdateHotelUseCase updateHotelUseCase;
    private final GetAllHotelsUseCase getAllHotelsUseCase;
    private final GetHotelByIdUseCase getHotelByIdUseCase;
    private final GetHotelsByUserIdUseCase getHotelsByUserIdUseCase;
    private final UpdateHotelStatusUseCase updateHotelStatusUseCase;
    private final DeleteHotelUseCase deleteHotelUseCase;

    @GetMapping("/")
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        List<HotelDto> hotelDtoList = getAllHotelsUseCase.execute();
        return ResponseEntity.ok(hotelDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(getHotelByIdUseCase.execute(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HotelDto>> getHotelsByUserId(@PathVariable Long userId) {
        List<HotelDto> hotelDtoList = getHotelsByUserIdUseCase.execute(userId);
        return ResponseEntity.ok(hotelDtoList);
    }

    @PostMapping("/create")
    public ResponseEntity<HotelDto> createHotel(@Valid @RequestBody HotelDto hotelDto) {
        return ResponseEntity.ok(createHotelUseCase.execute(hotelDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> updateHotel(@PathVariable Long id, @RequestBody HotelDto hotelDto) {
        return ResponseEntity.ok(updateHotelUseCase.execute(id, hotelDto));
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<HotelDto> enableHotel(@PathVariable Long id, @RequestParam StatusDto statusDto) {
        return ResponseEntity.ok(updateHotelStatusUseCase.execute(id, statusDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        deleteHotelUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}