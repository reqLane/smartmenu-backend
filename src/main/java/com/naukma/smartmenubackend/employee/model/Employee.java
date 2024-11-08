package com.naukma.smartmenubackend.employee.model;

import com.naukma.smartmenubackend.employee.role.EmployeeRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 100)
    @Email(message = "INVALID USER EMAIL FORMAT")
    private String email;

    @Column(nullable = false, length = 72)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (getRole() == EmployeeRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_WAITER"));
        return List.of(new SimpleGrantedAuthority("ROLE_WAITER"));
    }

    @Override
    public String getUsername() {
        return getName() + "_" + getEmployeeId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
