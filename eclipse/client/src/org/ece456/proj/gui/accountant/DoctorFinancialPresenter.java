package org.ece456.proj.gui.accountant;

import java.util.Date;

import org.ece456.proj.orm.objects.Doctor;

public interface DoctorFinancialPresenter {

    void searchAppointments(Date start, Date end);

    void show(Doctor doctor);
}