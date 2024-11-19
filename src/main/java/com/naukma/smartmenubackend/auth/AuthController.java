package com.naukma.smartmenubackend.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }


}
