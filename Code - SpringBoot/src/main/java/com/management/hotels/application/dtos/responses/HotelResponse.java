package com.management.hotels.application.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.hotels.application.dtos.enums.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @JsonProperty("id")
    private long hotelId;

    @JsonProperty("name")
    private String hotelName;

    private String address;

    private String city;

    private StatusDto status;

    private UserResponse createdBy;

}