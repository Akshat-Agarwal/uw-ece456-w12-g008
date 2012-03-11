package org.ece456.proj.orm.objects;

public enum StaffSearchOption {
    ID("Staff ID"),
    NAME("Name");

    private final String pretty;

    StaffSearchOption(String pretty) {
        this.pretty = pretty;
    }

    @Override
    public String toString() {
        return pretty;
    }
}
