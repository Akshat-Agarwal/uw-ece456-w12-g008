package org.ece456.proj.gui.search.doctor;

import java.util.List;

import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.DoctorSearchOption;

public interface DoctorSearchPresenter {

    void show();

    List<Doctor> search(DoctorSearchOption field, String text);

    void onSelection(Doctor selected);

    void onCancel();
}
