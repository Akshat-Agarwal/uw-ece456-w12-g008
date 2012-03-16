package org.ece456.proj.gui.accountant;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.shared.Connection;

public class DoctorFinancialPresenterImpl implements DoctorFinancialPresenter {

    private final Connection connection;

    private DoctorFinancialView view;

    private final Id<Doctor> doctorId;

    public DoctorFinancialPresenterImpl(Connection connection, Id<Doctor> doctorId) {
        this.connection = connection;
        this.doctorId = doctorId;
    }

    @Override
    public void searchAppointments(Date start, Date end) {
        // Start and/or end can be null.
        List<Appointment> apps = Collections.emptyList();
        try {
            apps = connection.getServer().getAppointmentsForDoctor(connection.getSession(),
                    doctorId, start, end);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        view.fillAppointments(apps);
    }

    @Override
    public void show(Doctor doctor) {
        if (view == null) {
            view = new DoctorFinancialView(this);
        }

        view.fillData(doctor);

        view.setVisible(true);
    }

}
