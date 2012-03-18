package org.ece456.proj.gui.accountant;

import java.util.Date;

import org.ece456.proj.orm.objects.Patient;

public interface PatientFinancialPresenter {

    void searchAppointments(Date start, Date end);

    void show(Patient patient);
}
