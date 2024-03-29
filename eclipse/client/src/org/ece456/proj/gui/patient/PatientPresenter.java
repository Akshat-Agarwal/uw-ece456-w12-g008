package org.ece456.proj.gui.patient;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientContact;

public interface PatientPresenter {

    void show(Id<Patient> id);

    void hide();

    void savePersonalData(PatientContact patientContact);

    void refresh();

    void showPasswordChange();

}
