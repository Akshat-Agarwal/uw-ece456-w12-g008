package org.ece456.proj.gui.staff;

import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Patient;

public interface AppointmentListPresenter {

    void show(Patient patient);

    void show(Doctor doctor);

    void updateAppointment(Appointment app);

    void newAppointment();

    void refresh();

    void deleteAppointment(Appointment app);
}
