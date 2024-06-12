package com.management.hotels.application.ports.services;

import com.management.hotels.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserToUserDetailsApplicationPortService {

    UserDetails converter(User user);

}