package org.ece456.proj.orm.query.jdbc;

import java.util.List;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.Patient.PatientContact;
import org.ece456.proj.orm.query.PatientQuery;

public class PatientQueryJdbc implements PatientQuery {

    @Override
    public Patient getPatientById(Id<Patient> id) {
        // TODO actually connect to DB and return results
        Patient p = new Patient();
        p.patient_id = Id.of(1234);
        p.contact.name = "Homer Simpson";
        p.contact.address = "742 Evergreen Terrace";
        p.contact.phone_num = "555-1234";
        return p;
    }

    @Override
    public List<Patient> getPatientsByNameExact(String name) {
        // TODO actually connect to DB and return results
        return null;
    }

    @Override
    public void getUpdatePatientContact(Id<Patient> id, PatientContact contact) {
        // TODO actually connect to DB and update the patient records
        System.out.println("Updated " + contact.name);
    }
}
