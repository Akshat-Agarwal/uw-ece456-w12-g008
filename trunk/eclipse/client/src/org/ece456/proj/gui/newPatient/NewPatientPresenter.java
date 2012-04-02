package org.ece456.proj.gui.newPatient;

import org.ece456.proj.orm.objects.Patient;

public interface NewPatientPresenter {

    void show(Patient patient);

    void updatePatient();

    void createPatient();
}
