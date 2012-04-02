package org.ece456.proj.gui.doctor;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.ece456.proj.gui.appointment.AppointmentView;
import org.ece456.proj.gui.appointment.AppointmentView.AppointmentPresenter;
import org.ece456.proj.gui.patient.PatientPresenter;
import org.ece456.proj.gui.patient.PatientPresenterImpl;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.shared.Connection;

public class PatientDoctorPresenterImpl implements PatientDoctorPresenter {

    private final Connection connection;

    private PatientDoctorView view;

    public PatientDoctorPresenterImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void searchAppointments(Date start, Date end) {
        // Start and/or end can be null.

    }

    @Override
    public void show(Patient patient) {
        List<Appointment> apps = Collections.emptyList();
        try {
            apps = connection.getServer().getAppointmentsForPatient(connection.getSession(),
                    patient.getPatientId(), null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (view == null) {
            view = new PatientDoctorView(this);
        }

        for (Appointment a : apps) {
            a.getPatient().getContact().setName(patient.getName());
        }

        view.fillAppointments(apps);

        view.fillData(patient);

        view.setVisible(true);
    }

    @Override
    public void saveAppointment(Appointment app) {
        try {
            connection.getServer().createAppointment(connection.getSession(), app);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AppointmentPresenter getAppointmentPresenter() {
        return new AppointmentView.AppointmentPresenter() {
            @Override
            public void viewPatient(Id<Patient> id) {
                PatientPresenter p = new PatientPresenterImpl(connection);
                p.show(id);
            }
        };
    }

    @Override
    public void viewPatient(Id<Patient> patientId) {
        PatientPresenter p = new PatientPresenterImpl(connection);
        p.show(patientId);
    }
}
