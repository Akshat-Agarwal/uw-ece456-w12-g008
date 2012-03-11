package org.ece456.proj.orm.objects;

public enum DoctorSearchOption {

    ID("Doctor ID"),
    NAME("Name");

    private final String pretty;

    DoctorSearchOption(String pretty) {
        this.pretty = pretty;
    }

    @Override
    public String toString() {
        return pretty;
    }
}
