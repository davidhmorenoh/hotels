package com.management.hotels.infrastructure.adapters.web;

import com.management.hotels.application.dtos.requests.HotelRequest;
import com.management.hotels.application.dtos.responses.HotelResponse;
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
    private final EnableHotelUseCase enableHotelUseCase;
    private final DisableHotelUseCase disableHotelUseCase;
    private final DeleteHotelUseCase deleteHotelUseCase;

    @GetMapping("/")
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        List<HotelResponse> hotelResponses = getAllHotelsUseCase.execute();
        return ResponseEntity.ok(hotelResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable Long id, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(getHotelByIdUseCase.execute(id, userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HotelResponse>> getHotelsByUserId(@PathVariable Long userId) {
        List<HotelResponse> hotelResponses = getHotelsByUserIdUseCase.execute(userId);
        return ResponseEntity.ok(hotelResponses);
    }

    @PostMapping("/create")
    public ResponseEntity<HotelResponse> createHotel(@Valid @RequestBody HotelRequest hotelRequest) {
        return ResponseEntity.ok(createHotelUseCase.execute(hotelRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelResponse> updateHotel(@PathVariable Long id, @Valid @RequestBody HotelRequest hotelRequest) {
        return ResponseEntity.ok(updateHotelUseCase.execute(id, hotelRequest));
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<HotelResponse> enableHotel(@PathVariable Long id, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(enableHotelUseCase.execute(id, userId));
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<HotelResponse> disableHotel(@PathVariable Long id, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(disableHotelUseCase.execute(id, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id, @RequestParam("userId") Long userId) {
        deleteHotelUseCase.execute(id, userId);
        return ResponseEntity.noContent().build();
    }

}