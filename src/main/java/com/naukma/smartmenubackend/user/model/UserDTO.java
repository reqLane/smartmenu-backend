package com.naukma.smartmenubackend.user.model;

import com.naukma.smartmenubackend.user.role.UserRole;

public record UserDTO(
        Long userId,
        String email,
        UserRole role
) {
}
