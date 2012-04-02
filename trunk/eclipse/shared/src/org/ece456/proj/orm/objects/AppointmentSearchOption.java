package org.ece456.proj.orm.objects;

public enum AppointmentSearchOption {
    PATIENT_NAME("Patient"),
    DIAGNOSIS("Diagnosis"),
    PRESCRIPTION("Prescription"),
    PROCEDURE("Procedure"),
    COMMENT("Comment");

    private final String pretty;

    AppointmentSearchOption(String pretty) {
        this.pretty = pretty;
    }

    @Override
    public String toString() {
        return pretty;
    }
}
