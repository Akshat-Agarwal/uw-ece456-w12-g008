package org.ece456.proj.gui.patient;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.Patient.PatientContact;

public class PatientPresenterImpl implements PatientPresenter {

    private final String username;

    public PatientPresenterImpl(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Patient getPatient(Id<Patient> id) {
        return null;
    }

    @Override
    public void updatePatientContact(Id<Patient> id, PatientContact contact) {
    }
}
