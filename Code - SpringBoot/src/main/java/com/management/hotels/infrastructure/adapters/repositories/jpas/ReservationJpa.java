package com.management.hotels.infrastructure.adapters.repositories.jpas;

import com.management.hotels.domain.entities.Hotel;
import com.management.hotels.domain.entities.Reservation;
import com.management.hotels.domain.entities.Room;
import com.management.hotels.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationJpa extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRoom(Room room);

    List<Reservation> findByUser(User traveler);

    @Query("SELECT r FROM Reservation r WHERE r.room.hotel = :hotel")
    List<Reservation> findByHotel(@Param("hotel") Hotel hotel);

    @Query("SELECT r FROM Reservation r WHERE r.room.roomId = :roomId AND r.state = 'Confirmed' AND " +
            "((r.checkInDate <= :checkOutDate AND r.checkOutDate >= :checkInDate))")
    List<Reservation> findConflictingReservations(@Param("roomId") long roomId, @Param("checkInDate") Date checkInDate, @Param("checkOutDate") Date checkOutDate);

}