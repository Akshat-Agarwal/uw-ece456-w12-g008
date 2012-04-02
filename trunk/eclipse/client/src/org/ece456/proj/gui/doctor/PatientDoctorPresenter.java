package org.ece456.proj.gui.doctor;

import java.util.Date;

import org.ece456.proj.gui.appointment.AppointmentView;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;

public interface PatientDoctorPresenter {

    void searchAppointments(Date start, Date end);

    void show(Patient patient);

    void saveAppointment(Appointment app);

    AppointmentView.AppointmentPresenter getAppointmentPresenter();

    void viewPatient(Id<Patient> patientId);
}
