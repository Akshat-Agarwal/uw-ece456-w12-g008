package org.ece456.proj.gui.accountant;

import java.util.Date;
import java.util.List;

import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Patient;

public interface FinancialPresenter {

    void searchAppointments(Date start, Date end);

    void show(Doctor doctor, List<Patient> patients);
}
