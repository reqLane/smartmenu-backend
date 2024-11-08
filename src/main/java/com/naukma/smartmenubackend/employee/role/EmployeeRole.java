package com.naukma.smartmenubackend.employee.role;

public enum EmployeeRole {
    ADMIN("ADMIN"),
    WAITER("WAITER");

    private String role;

    EmployeeRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
