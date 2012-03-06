package org.ece456.proj.gui.patient;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.Patient.PatientContact;
import org.ece456.proj.orm.query.QueryManager;

public class PatientPresenterImpl implements PatientPresenter {

    private final QueryManager query;
    private final String username;

    public PatientPresenterImpl(QueryManager query, String username) {
        this.query = query;
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Patient getPatient(Id<Patient> id) {
        return query.getPatientQuery().getPatientById(id);
    }

    @Override
    public void updatePatientContact(Id<Patient> id, PatientContact contact) {
        query.getPatientQuery().getUpdatePatientContact(id, contact);
    }
}
