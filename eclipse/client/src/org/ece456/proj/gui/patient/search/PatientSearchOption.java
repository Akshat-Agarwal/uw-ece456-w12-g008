package org.ece456.proj.gui.patient.search;

public enum PatientSearchOption {

    ID("Patient ID", MatchOption.EXACT),
    SIN("SIN", MatchOption.EXACT),
    HEALTH_CARD("Health Card", MatchOption.EXACT),
    NAME("Name", MatchOption.LIKE);

    private final String pretty;
    private final MatchOption match;

    PatientSearchOption(String pretty, MatchOption match) {
        this.pretty = pretty;
        this.match = match;
    }

    @Override
    public String toString() {
        return pretty;
    }

    public MatchOption getMatchOption() {
        return match;
    }
}
