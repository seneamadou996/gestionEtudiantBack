package com.amadou.gestionEtudiant.service.auth.impl;

import com.amadou.gestionEtudiant.dto.auth.AuthenticationRequest;
import com.amadou.gestionEtudiant.dto.auth.AuthenticationResponse;
import com.amadou.gestionEtudiant.model.auth.ExtendedUser;
import com.amadou.gestionEtudiant.service.auth.ApplicationUserDetailsService;
import com.amadou.gestionEtudiant.service.auth.AuthenticationService;
import com.amadou.gestionEtudiant.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final ApplicationUserDetailsService userDetailsService;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var userDetais = userDetailsService.loadUserByUsername(request.getUsername());
        var jwtToken = jwtUtils.generateToken((ExtendedUser) userDetais);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }
}
