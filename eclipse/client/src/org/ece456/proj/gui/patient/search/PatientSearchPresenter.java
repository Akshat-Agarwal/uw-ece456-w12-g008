package org.ece456.proj.gui.patient.search;

import java.util.List;

import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientSearchOption;

public interface PatientSearchPresenter {

    void show();

    List<Patient> search(PatientSearchOption field, String text);

    void onSelection(Patient selected);

    void onCancel();
}
