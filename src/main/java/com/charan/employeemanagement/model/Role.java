package com.charan.employeemanagement.model;

public enum Role {
    ADMIN,
    HR_MANAGER,
    EMPLOYEE,
    ACCOUNTANT,
    DEVELOPER,
    TESTER,
    TRAINEE;

    // Optional: Add display names if needed
    public String getDisplayName() {
        return switch (this) {
            case ADMIN -> "Administrator";
            case HR_MANAGER -> "HR Manager";
            case EMPLOYEE -> "Employee";
            case ACCOUNTANT -> "Accountant";
            case DEVELOPER -> "Developer";
            case TESTER -> "Tester";
            case TRAINEE -> "Trainee";
        };
    }
}
