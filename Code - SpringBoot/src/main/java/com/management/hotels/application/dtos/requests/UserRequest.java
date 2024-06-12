package com.management.hotels.application.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.hotels.application.dtos.enums.UserTypeDto;
import com.management.hotels.application.validation.ValidEnum;
import jakarta.validation.constraints.Email;
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
public class UserRequest implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    private Long userId;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;

    @NotBlank(message = "User email is mandatory")
    @Email(message = "User email should be valid")
    @Size(max = 100, message = "User email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "User password is mandatory")
    @Size(min = 6, max = 100, message = "User password must be between 6 and 100 characters")
    private String password;

    @NotNull(message = "User type is mandatory")
    @ValidEnum(enumClass = UserTypeDto.class, message = "User type must be one of the predefined values: Agent or Traveler")
    @JsonProperty("type")
    private UserTypeDto userType;

}