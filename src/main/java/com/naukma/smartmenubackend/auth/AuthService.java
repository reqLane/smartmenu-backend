package com.naukma.smartmenubackend.auth;

import com.naukma.smartmenubackend.employee.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final EmployeeService employeeService;

    public AuthService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // BUSINESS LOGIC


    // UserDetailsService

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return employeeService.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("USER WITH EMAIL %s NOT FOUND", email)));
    }
}