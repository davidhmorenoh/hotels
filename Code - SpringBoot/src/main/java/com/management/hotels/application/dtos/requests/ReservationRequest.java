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
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequest implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @NotNull(message = "Reservation check in date is mandatory")
    @FutureOrPresent(message = "Reservation check in date must be equal or after to current date")
    private Date checkInDate;

    @NotNull(message = "Reservation check out date is mandatory")
    @FutureOrPresent(message = "Reservation check out date must be equal or after to current date")
    private Date checkOutDate;

    @ValidEnum(enumClass = StateDto.class, message = "Reservation state must be one of the predefined values: Confirmed or Cancelled")
    @JsonProperty("state")
    private StateDto stateDto;

    @NotNull(message = "Reservation roomId is mandatory")
    private long roomId;

    @NotNull(message = "Reservation travelerId is mandatory")
    private long travelerId;

}