package com.management.hotels.application.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.hotels.application.dtos.enums.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class RoomResponse implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @JsonProperty("id")
    private long roomId;

    @JsonProperty("type")
    private String roomType;

    private String location;

    private double baseCost;

    private double taxes;

    private int capacity;

    @JsonProperty("status")
    private StatusDto statusDto;

    private HotelResponse hotel;

}