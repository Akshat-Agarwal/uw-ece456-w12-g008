package org.ece456.proj.gui.patient;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientContact;

public interface PatientPresenter {

    void updatePatientContact(Id<Patient> id, PatientContact contact);

    void show(Patient patient);

    void hide();

}
