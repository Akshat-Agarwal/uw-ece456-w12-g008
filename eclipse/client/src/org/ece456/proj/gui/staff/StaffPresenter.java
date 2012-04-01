package org.ece456.proj.gui.staff;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.PatientContact;
import org.ece456.proj.orm.objects.Staff;

public interface StaffPresenter {

    void show(Id<Staff> id);

    void hide();

    void savePersonalData(PatientContact patientContact);

    void refresh();

    void showPasswordChange();

    void showNewPatientView();

    void showPatientSearch(int type);

    void showDoctorSearch(int type);
}
