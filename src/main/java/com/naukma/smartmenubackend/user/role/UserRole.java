package com.naukma.smartmenubackend.user.role;

public enum UserRole {
    ADMIN("ADMIN"),
    OPERATOR("OPERATOR");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
