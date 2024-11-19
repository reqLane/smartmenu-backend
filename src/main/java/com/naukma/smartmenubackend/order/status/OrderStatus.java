package com.naukma.smartmenubackend.order.status;

public enum OrderStatus {
    PENDING("PENDING"),
    COOKED("COOKED"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
