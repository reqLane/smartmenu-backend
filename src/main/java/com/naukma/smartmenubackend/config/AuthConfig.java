package com.naukma.smartmenubackend.config;

import com.naukma.smartmenubackend.auth.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthConfig {
    private final SecurityFilter securityFilter;

    public AuthConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers("/api/auth/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/menu-items").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "api/menu-items/{menuItemId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "api/menu-items/{menuItemId}").hasRole("ADMIN")
                        .requestMatchers("api/menu-items/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/orders").hasRole("OPERATOR")
                        .requestMatchers(HttpMethod.PATCH, "api/orders/{orderId}/pay").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "api/orders/{orderId}/cancel").hasRole("OPERATOR")
                        .requestMatchers(HttpMethod.GET, "/api/orders/pending").hasRole("OPERATOR")
                        .requestMatchers(HttpMethod.GET, "api/orders/active").hasRole("OPERATOR")

                        .requestMatchers(HttpMethod.PATCH, "/api/order-items/{orderItemId}/done").hasRole("OPERATOR")

                        .requestMatchers(HttpMethod.POST, "api/reviews").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/reviews").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "api/tables").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "api/tables").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "api/tables").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "api/tables/{tableId}/has-active-order").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/tables/{tableId}/get-active-order").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "api/users/{userId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "api/users/{userId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "api/users").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/waiters").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "api/waiters/{waiterId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "api/waiters/{waiterId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "api/waiters").hasRole("ADMIN")

                        .anyRequest().permitAll())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}