package com.management.hotels.application.services.authentication;

import com.management.hotels.application.ports.services.UserToUserDetailsApplicationPortService;
import com.management.hotels.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserToUserDetailsApplicationService implements UserToUserDetailsApplicationPortService {

    @Override
    public UserDetails converter(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList());
    }

}