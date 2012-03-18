package org.ece456.proj.gui.accountant;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.shared.Connection;

public class PatientFinancialPresenterImpl implements PatientFinancialPresenter {

    private final Connection connection;

    private PatientFinancialView view;

    private final Id<Patient> patientId;

    public PatientFinancialPresenterImpl(Connection connection, Id<Patient> patientId) {
        this.connection = connection;
        this.patientId = patientId;
    }

    @Override
    public void searchAppointments(Date start, Date end) {
        // Start and/or end can be null.
        List<Appointment> apps = Collections.emptyList();
        try {
            apps = connection.getServer().getAppointmentsForPatient(connection.getSession(),
                    patientId, start, end);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        view.fillAppointments(apps);
    }

    @Override
    public void show(Patient patient) {
        if (view == null) {
            view = new PatientFinancialView(this);
        }

        view.fillData(patient);

        view.setVisible(true);
    }
}
