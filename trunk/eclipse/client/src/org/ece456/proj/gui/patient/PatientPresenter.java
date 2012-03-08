package org.ece456.proj.gui.patient;

import java.util.List;

import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientContact;

public interface PatientPresenter {

    void updatePatientContact(Id<Patient> id, PatientContact contact);

    void show(Patient patient, List<Appointment> appointments);

    void hide();

}
