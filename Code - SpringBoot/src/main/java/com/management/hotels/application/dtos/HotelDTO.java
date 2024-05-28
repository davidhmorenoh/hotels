package com.management.hotels.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelDTO {

    private Long hotelId;
    @NotBlank(message = "Hotel name is mandatory")
    @Size(max = 100, message = "Hotel name must not exceed 100 characters")
    private String hotelName;
    @NotBlank(message = "Address is mandatory")
    private String address;
    @NotBlank(message = "City is mandatory")
    private String city;
    private String status;
    private Long createdBy;

}