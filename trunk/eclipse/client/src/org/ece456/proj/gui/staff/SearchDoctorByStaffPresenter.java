package org.ece456.proj.gui.staff;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.shared.table.SelectionListener;
import org.ece456.proj.gui.shared.table.SelectionListener.AfterAction;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.DoctorSearchOption;
import org.ece456.proj.orm.objects.Staff;
import org.ece456.proj.shared.Connection;

public class SearchDoctorByStaffPresenter implements SearchPresenter<Doctor> {

    private final Connection connection;
    private final SelectionListener<Doctor> listener;
    private final Staff staff;
    private SearchDoctorByStaffView view;

    public SearchDoctorByStaffPresenter(Connection connection, SelectionListener<Doctor> listener,
            Staff staff) {
        this.connection = connection;
        this.listener = listener;
        this.staff = staff;
    }

    @Override
    public List<Doctor> search() {
        try {
            return connection.getServer().searchDoctorByStaff(connection.getSession(),
                    staff.getStaffId(), null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Doctor> search(Object field, String text) {
        try {
            return connection.getServer().searchDoctorByStaff(connection.getSession(),
                    staff.getStaffId(), (DoctorSearchOption) field, text);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void show() {
        if (view == null) {
            view = new SearchDoctorByStaffView(this);
        }

        view.setVisible(true);
    }

    @Override
    public void onSelection(Doctor selected) {
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
