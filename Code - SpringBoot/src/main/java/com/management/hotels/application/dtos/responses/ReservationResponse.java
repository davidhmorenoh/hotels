package com.management.hotels.application.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.hotels.application.dtos.enums.StateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @JsonProperty("id")
    private long reservationId;

    private Date checkInDate;

    private Date checkOutDate;

    private int numberOfGuests;

    @JsonProperty("date")
    private Timestamp reservationDate;

    private StateDto state;

    private RoomResponse room;

    private UserResponse traveler;

}