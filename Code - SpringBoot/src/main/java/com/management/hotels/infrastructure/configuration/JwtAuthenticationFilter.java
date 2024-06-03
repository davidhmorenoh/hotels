package com.management.hotels.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.hotels.application.dtos.requests.AuthenticationRequest;
import com.management.hotels.application.dtos.responses.AuthenticationResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenConfig jwtTokenConfig;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenConfig jwtTokenConfig, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenConfig = jwtTokenConfig;
        this.customUserDetailsService = customUserDetailsService;
        setFilterProcessesUrl("/api/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AuthenticationRequest authRequest = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String jwt = jwtTokenConfig.generateToken(userDetails);
        AuthenticationResponse authResponse = new AuthenticationResponse(jwt);

        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(authResponse));
        response.getWriter().flush();
    }

}