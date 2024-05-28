package com.management.hotels.infrastructure.adapters.repositories;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotelAndStatus(Hotel hotel, Status status);

    List<Room> findByHotel_HotelId(Long hotelId);

}