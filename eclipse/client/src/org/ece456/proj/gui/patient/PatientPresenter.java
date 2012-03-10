package org.ece456.proj.gui.patient;

import java.util.List;

import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientContact;

public interface PatientPresenter {

    void show(Patient patient, List<Appointment> appointments);

    void hide();

    void savePersonalData(PatientContact patientContact);

}
