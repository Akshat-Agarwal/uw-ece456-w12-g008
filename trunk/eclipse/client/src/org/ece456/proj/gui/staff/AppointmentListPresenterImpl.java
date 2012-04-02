package org.ece456.proj.gui.staff;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.shared.Connection;

public class AppointmentListPresenterImpl implements AppointmentListPresenter {

    private final Connection connection;

    private AppointmentListView view;

    private final Patient patient;
    private final Doctor doctor;

    public AppointmentListPresenterImpl(Connection connection, Patient patient, Doctor doctor) {
        this.connection = connection;
        this.patient = patient;
        this.doctor = doctor;
    }

    @Override
    public void show(Patient patient) {
        List<Appointment> apps = Collections.emptyList();
        try {
            apps = connection.getServer().getAppointmentsForPatient(connection.getSession(),
                    patient.getPatientId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (view == null) {
            view = new AppointmentListView(this);
        }
        for (Appointment a : apps) {
            a.getPatient().getContact().setName(patient.getName());
        }
        view.fillAppointments(apps);
        view.fillData(patient);
        // view.setType(displayType);
        view.setVisible(true);
    }

    @Override
    public void show(Doctor doctor) {
        List<Appointment> apps = Collections.emptyList();
        try {
            apps = connection.getServer().getAppointmentsForDoctor(connection.getSession(),
                    doctor.getDoctor_id(), null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (view == null) {
            view = new AppointmentListView(this);
        }
        for (Appointment a : apps) {
            a.getDoctor().setName(doctor.getName());
        }
        view.fillAppointments(apps);
        view.fillData(doctor);
        // view.setType(displayType);
        view.setVisible(true);
    }

    @Override
    public void newAppointment() {
        Appointment newApp = new Appointment();
        Doctor d = new Doctor();
        try {
            d = connection.getServer().getDoctorById(connection.getSession(),
                    patient.getMedical().getDefaultDoctor().getDoctor_id());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        newApp.setDoctor(d);
        newApp.setPatient(patient);
        AppointmentOpener ao = new AppointmentOpener(this);
        ao.newAppointment(newApp);
    }

    @Override
    public void updateAppointment(Appointment app) {
        try {
            connection.getServer().createAppointment(connection.getSession(), app);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() {
        if (patient != null)
            show(patient);
        else if (doctor != null)
            show(doctor);
    }
}
