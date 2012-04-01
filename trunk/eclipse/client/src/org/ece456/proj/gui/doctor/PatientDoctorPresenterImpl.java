package org.ece456.proj.gui.doctor;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.shared.Connection;

public class PatientDoctorPresenterImpl implements PatientDoctorPresenter {

    private final Connection connection;

    private PatientDoctorView view;

    private final Id<Patient> patientId;

    public PatientDoctorPresenterImpl(Connection connection, Id<Patient> patientId) {
        this.connection = connection;
        this.patientId = patientId;
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
                    patientId, null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (view == null) {
            view = new PatientDoctorView(this);
        }
        view.fillAppointments(apps);

        view.fillData(patient);

        view.setVisible(true);
    }

    @Override
    public void saveAppointment(Appointment app) {
        try {
            Boolean added = connection.getServer().createAppointment(connection.getSession(), app);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
