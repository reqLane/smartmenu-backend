package com.naukma.smartmenubackend.auth;

import com.naukma.smartmenubackend.auth.model.JWTTokenDTO;
import com.naukma.smartmenubackend.auth.model.SignInDTO;
import com.naukma.smartmenubackend.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public AuthController(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JWTTokenDTO> signIn(@RequestBody SignInDTO signInDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(signInDTO.email(), signInDTO.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        User authenticatedUser = (User) authUser.getPrincipal();
        var accessToken = tokenProvider.generateAccessToken(authenticatedUser);
        return ResponseEntity.ok(new JWTTokenDTO(accessToken));
    }
}
