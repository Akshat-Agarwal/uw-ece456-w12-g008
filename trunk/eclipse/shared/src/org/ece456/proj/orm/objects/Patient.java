package org.ece456.proj.orm.objects;

import java.io.Serializable;

public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    private Id<Patient> patientId;

    private final PatientContact contact;
    private final PatientMedical medical;

    public Patient() {
        this.contact = new PatientContact();
        this.medical = new PatientMedical();

        clear();
    }

    private void clear() {
        contact.clear();
        medical.clear();
    }

    public Id<Patient> getPatientId() {
        return patientId;
    }

    public void setPatientId(Id<Patient> id) {
        this.patientId = id;
    }

    public PatientContact getContact() {
        return contact;
    }

    public PatientMedical getMedical() {
        return medical;
    }

    public String getName() {
        return contact.getName();
    }
}
