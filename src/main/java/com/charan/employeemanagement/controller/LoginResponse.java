package com.charan.employeemanagement.controller;

public class LoginResponse {
    private String role;

    public LoginResponse(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
