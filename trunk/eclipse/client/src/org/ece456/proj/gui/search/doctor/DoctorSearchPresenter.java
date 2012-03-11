package org.ece456.proj.gui.search.doctor;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.shared.table.SelectionListener;
import org.ece456.proj.gui.shared.table.SelectionListener.AfterAction;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.DoctorSearchOption;
import org.ece456.proj.shared.Connection;

public class DoctorSearchPresenter implements SearchPresenter<Doctor> {

    private final Connection connection;
    private final SelectionListener<Doctor> listener;

    private DoctorSearchView view;

    public DoctorSearchPresenter(Connection connection, SelectionListener<Doctor> listener) {
        this.connection = connection;
        this.listener = listener;
    }

    @Override
    public List<Doctor> search(Object field, String text) {
        try {
            return connection.getServer().searchDoctors(connection.getSession(),
                    (DoctorSearchOption) field, text);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void show() {
        if (view == null) {
            view = new DoctorSearchView(this);
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
