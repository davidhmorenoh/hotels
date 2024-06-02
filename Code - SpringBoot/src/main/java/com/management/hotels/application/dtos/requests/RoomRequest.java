package com.management.hotels.application.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.validation.ValidEnum;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomRequest implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @NotBlank(message = "Room type is mandatory")
    @Size(min = 3, max = 50, message = "Room type must be between 3 and 50 characters")
    @JsonProperty("type")
    private String roomType;

    @NotBlank(message = "Room location is mandatory")
    @Size(min = 5, max = 50, message = "Room location must be between 5 and 255 characters")
    private String location;

    @NotNull(message = "Room base cost is mandatory")
    @Digits(integer = 10, fraction = 2, message = "Room base cost must be a valid decimal number with up to two decimal places")
    private double baseCost;

    @NotNull(message = "Room taxes are mandatory")
    @Digits(integer = 10, fraction = 2, message = "Room taxes must be a valid decimal number with up to two decimal places")
    private double taxes;

    @NotNull(message = "Room capacity is mandatory")
    private int capacity;

    @ValidEnum(enumClass = StatusDto.class, message = "Room status must be one of the predefined values: Enabled or Disabled")
    private StatusDto status;

    @NotNull(message = "Room hotelId is mandatory")
    private long hotelId;

}