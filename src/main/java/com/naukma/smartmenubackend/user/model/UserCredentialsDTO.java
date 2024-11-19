package com.naukma.smartmenubackend.user.model;

import com.naukma.smartmenubackend.user.role.UserRole;

public record UserCredentialsDTO(
        String email,
        String password,
        UserRole role
) {
}
