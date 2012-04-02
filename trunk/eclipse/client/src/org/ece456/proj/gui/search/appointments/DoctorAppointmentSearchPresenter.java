package org.ece456.proj.gui.search.appointments;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.shared.table.SelectionListener;
import org.ece456.proj.gui.shared.table.SelectionListener.AfterAction;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.AppointmentSearchOption;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.shared.Connection;

public class DoctorAppointmentSearchPresenter implements SearchPresenter<Appointment> {
    private final Connection connection;
    private final SelectionListener<Appointment> listener;

    private DoctorAppointmentSearchView view;
    private final Id<Doctor> doctor;

    public DoctorAppointmentSearchPresenter(Connection connection,
            SelectionListener<Appointment> listener, Id<Doctor> doctor) {
        this.connection = connection;
        this.listener = listener;
        this.doctor = doctor;
    }

    @Override
    public List<Appointment> search() {
        try {
            return connection.getServer().searchDoctorAppointments(connection.getSession(), doctor,
                    null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Appointment> search(Object field, String text) {
        try {
            return connection.getServer().searchDoctorAppointments(connection.getSession(), doctor,
                    (AppointmentSearchOption) field, text);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void show() {
        if (view == null) {
            view = new DoctorAppointmentSearchView(this);
        }

        view.setVisible(true);
    }

    @Override
    public void onSelection(Appointment selected) {
        AfterAction a = listener.onSelection(selected);
        if (a == AfterAction.CLOSE_DIALOG) {
            view.dispose();
        }
    }

    @Override
    public void onCancel() {
        listener.onCancel();
        view.dispose();
    }
}
