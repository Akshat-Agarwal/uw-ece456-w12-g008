package org.ece456.proj.orm.objects;

public enum UserRole {

    PATIENT("Patient"),
    DOCTOR("Doctor"),
    STAFF("Staff Member"),
    ACCOUNTANT("Accountant"),
    LEGAL("Legal");

    private String displayString;

    private UserRole(String displayString) {
        this.displayString = displayString;
    }

    @Override
    public String toString() {
        return displayString;
    }
}
