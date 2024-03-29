package org.ece456.proj.orm.objects;

public enum PatientSearchOption {

    ID("Patient ID"),
    SIN("SIN"),
    HEALTH_CARD("Health Card"),
    NAME("Name");

    private final String pretty;

    PatientSearchOption(String pretty) {
        this.pretty = pretty;
    }

    @Override
    public String toString() {
        return pretty;
    }
}
