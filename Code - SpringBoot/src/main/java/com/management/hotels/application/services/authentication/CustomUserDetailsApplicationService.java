package com.management.hotels.application.services.authentication;

import com.management.hotels.application.ports.services.UserToUserDetailsApplicationPortService;
import com.management.hotels.domain.entities.User;
import com.management.hotels.domain.ports.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsApplicationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserToUserDetailsApplicationPortService userToUserDetailsApplicationPortService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return userToUserDetailsApplicationPortService.converter(user);
    }

}