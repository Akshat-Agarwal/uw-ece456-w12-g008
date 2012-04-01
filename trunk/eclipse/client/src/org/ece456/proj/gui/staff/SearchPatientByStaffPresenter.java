package org.ece456.proj.gui.staff;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.shared.table.SelectionListener;
import org.ece456.proj.gui.shared.table.SelectionListener.AfterAction;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientSearchOption;
import org.ece456.proj.orm.objects.Staff;
import org.ece456.proj.shared.Connection;

public class SearchPatientByStaffPresenter implements SearchPresenter<Patient> {

    private final Connection connection;
    private final SelectionListener<Patient> listener;
    private final Staff staff;
    private SearchPatientByStaffView view;

    public SearchPatientByStaffPresenter(Connection connection,
            SelectionListener<Patient> listener, Staff staff) {
        this.connection = connection;
        this.listener = listener;
        this.staff = staff;
    }

    @Override
    public List<Patient> search() {
        try {
            return connection.getServer().searchPatientByStaff(connection.getSession(),
                    staff.getStaffId(), null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Patient> search(Object field, String text) {
        try {
            return connection.getServer().searchPatientByStaff(connection.getSession(),
                    staff.getStaffId(), (PatientSearchOption) field, text);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void show() {
        if (view == null) {
            view = new SearchPatientByStaffView(this);
        }

        view.setVisible(true);
    }

    @Override
    public void onSelection(Patient selected) {
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
