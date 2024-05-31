package com.management.hotels.application.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.application.validation.ValidEnum;
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
public class HotelRequest implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @NotBlank(message = "Hotel name is mandatory")
    @Size(max = 100, message = "Hotel name must not exceed 100 characters")
    @JsonProperty("name")
    private String hotelName;

    @NotBlank(message = "Hotel address is mandatory")
    @Size(min = 3, max = 255, message = "Hotel address must be between 3 and 255 characters")
    private String address;

    @NotBlank(message = "Hotel city is mandatory")
    @Size(min = 3, max = 100, message = "Hotel city must be between 3 and 100 characters")
    private String city;

    @ValidEnum(enumClass = StatusDto.class, message = "Hotel status must be one of the predefined values: Enabled or Disabled")
    private StatusDto status;

    @NotNull(message = "Hotel createdBy is mandatory")
    private long createdBy;

}