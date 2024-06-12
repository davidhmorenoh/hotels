package com.management.hotels.application.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.hotels.application.dtos.enums.StateDto;
import com.management.hotels.application.validation.ValidEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequest implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    private Long reservationId;

    @NotNull(message = "Reservation check in date is mandatory")
    @FutureOrPresent(message = "Reservation check in date must be equal or after to current date")
    private Date checkInDate;

    @NotNull(message = "Reservation check out date is mandatory")
    @FutureOrPresent(message = "Reservation check out date must be equal or after to current date")
    private Date checkOutDate;

    private int numberOfGuests;

    @JsonProperty("date")
    private Timestamp reservationDate;

    @ValidEnum(enumClass = StateDto.class, message = "Reservation state must be one of the predefined values: Confirmed or Cancelled")
    private StateDto state;

    @NotNull(message = "Reservation roomId is mandatory")
    private long roomId;

    @NotNull(message = "Reservation travelerId is mandatory")
    @JsonProperty("travelerId")
    private long userId;

    @NotNull(message = "Reservation emergency contacts is mandatory")
    private List<EmergencyContactRequest> emergencyContacts;

    @NotNull(message = "Reservation guests is mandatory")
    private List<GuestRequest> guests;

}