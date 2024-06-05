package com.management.hotels.infrastructure.adapters.repositories.jpas;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RoomJpa extends JpaRepository<Room, Long> {

    List<Room> findByHotel(Hotel hotel);

    @Query("SELECT r FROM Room r WHERE r.hotel.city = :city AND r.status = 'Enabled' AND r.capacity >= :capacity " +
            "AND r.roomId NOT IN (SELECT res.room.roomId FROM Reservation res WHERE res.state = 'Confirmed' AND " +
            "((:checkInDate BETWEEN res.checkInDate AND res.checkOutDate) OR " +
            "(:checkOutDate BETWEEN res.checkInDate AND res.checkOutDate) OR " +
            "(res.checkInDate BETWEEN :checkInDate AND :checkOutDate) OR " +
            "(res.checkOutDate BETWEEN :checkInDate AND :checkOutDate)))")
    List<Room> findAvailableRooms(@Param("city") String city, @Param("checkInDate") Date checkInDate, @Param("checkOutDate") Date checkOutDate, @Param("capacity") int capacity);

}