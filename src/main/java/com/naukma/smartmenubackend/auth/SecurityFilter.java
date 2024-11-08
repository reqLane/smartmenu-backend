package com.naukma.smartmenubackend.auth;

import com.naukma.smartmenubackend.employee.EmployeeService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final EmployeeService employeeService;

    public SecurityFilter(TokenProvider tokenProvider, EmployeeService employeeService) {
        this.tokenProvider = tokenProvider;
        this.employeeService = employeeService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var email = tokenProvider.validateToken(token);

            if (email != null) {
                var employee = employeeService.findByEmail(email).orElse(null);

                if (employee != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(employee, null, employee.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}