package org.ece456.proj.gui.patient.search;

import java.util.List;

import org.ece456.proj.orm.objects.Patient;

public interface PatientSearchPresenter {

    void show();

    List<Patient> search(PatientSearchOption field, String text);

}
