package org.ece456.proj.gui.doctor;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.ece456.proj.gui.appointment.AppointmentView;
import org.ece456.proj.gui.appointment.AppointmentView.AppointmentPresenter;
import org.ece456.proj.gui.patient.PatientPresenter;
import org.ece456.proj.gui.patient.PatientPresenterImpl;
import org.ece456.proj.gui.search.doctor.DoctorSearchPresenter;
import org.ece456.proj.gui.shared.table.SelectionListener;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.shared.Connection;

public class PatientDoctorPresenterImpl implements PatientDoctorPresenter {

    private final Connection connection;

    private PatientDoctorView view;

    private Patient patient;

    public PatientDoctorPresenterImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void searchAppointments(Date start, Date end) {
        // Start and/or end can be null.

    }

    @Override
    public void show(Patient patient) {
        this.patient = patient;
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

        List<Doctor> consultants = Collections.emptyList();
        try {
            consultants = connection.getServer().getConsultantsForPatient(connection.getSession(),
                    patient.getPatientId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        view.fillConsultants(consultants);

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

    @Override
    public void addConsultant() {
        DoctorSearchPresenter p = new DoctorSearchPresenter(connection,
                new SelectionListener<Doctor>() {

                    @Override
                    public SelectionListener.AfterAction onSelection(Doctor selected) {

                        connection.getServer().addConsultantForPatient(connection.getSession(),
                                patient.getPatientId(), selected);

                        view.addConsultant(selected);
                        return SelectionListener.AfterAction.CLOSE_DIALOG;
                    }

                    @Override
                    public void onCancel() {
                        // Do nothing
                    }
                });
        p.show();
    }
}
