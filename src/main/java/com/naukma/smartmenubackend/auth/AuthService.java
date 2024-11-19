package com.naukma.smartmenubackend.auth;

import com.naukma.smartmenubackend.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    // BUSINESS LOGIC



    // UserDetailsService

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userService.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("USER WITH EMAIL %s NOT FOUND", email)));
    }
}