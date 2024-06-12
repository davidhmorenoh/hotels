package com.management.hotels.application.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
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
public class EmergencyContactRequest implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    private Long contactId;

    @NotBlank(message = "Emergency contact full name is mandatory")
    @Size(max = 200, message = "Emergency contact full name must not exceed 200 characters")
    private String fullName;

    @NotBlank(message = "Emergency contact phone is mandatory")
    @Size(max = 20, message = "Emergency contact phone must not exceed 20 characters")
    @JsonProperty("phone")
    private String contactPhone;

}