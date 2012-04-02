package org.ece456.proj.gui.doctor;

import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;

public interface DoctorPresenter {

    void show(Id<Doctor> id);

    void hide();

    void savePersonalData(Doctor doctor);

    void refresh();

    void showPasswordChange();

    void showAllPatientSearch();

    void showMyPatientSearch();

}
