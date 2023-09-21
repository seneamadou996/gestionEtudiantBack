package com.amadou.gestionEtudiant.service.auth;

import com.amadou.gestionEtudiant.dto.auth.AuthenticationRequest;
import com.amadou.gestionEtudiant.dto.auth.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

}
