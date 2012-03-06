package org.ece456.proj.orm.query;

import java.util.List;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.Patient.PatientContact;

public interface PatientQuery {
    Patient getPatientById(Id<Patient> id);

    void getUpdatePatientContact(Id<Patient> id, PatientContact contact);

    List<Patient> getPatientsByNameExact(String name);
}
