package org.ece456.proj.gui.patient;

public class PatientPresenterImpl implements PatientPresenter {
    private final String username;

    public PatientPresenterImpl(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
