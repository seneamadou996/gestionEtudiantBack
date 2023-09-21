package com.amadou.gestionEtudiant.controller;

import com.amadou.gestionEtudiant.dto.auth.AuthenticationRequest;
import com.amadou.gestionEtudiant.dto.auth.AuthenticationResponse;
import com.amadou.gestionEtudiant.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.amadou.gestionEtudiant.utils.Constants.AUTHENTICATION_ENDPOINT;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = AUTHENTICATION_ENDPOINT + "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

