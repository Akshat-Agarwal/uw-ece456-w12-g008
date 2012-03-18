package org.ece456.proj.gui.search.patient;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.shared.table.SelectionListener;
import org.ece456.proj.gui.shared.table.SelectionListener.AfterAction;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientSearchOption;
import org.ece456.proj.shared.Connection;

public class PatientSearchPresenter implements SearchPresenter<Patient> {

    private final Connection connection;
    private final SelectionListener<Patient> listener;

    private PatientSearchView view;

    public PatientSearchPresenter(Connection connection, SelectionListener<Patient> listener) {
        this.connection = connection;
        this.listener = listener;
    }

    @Override
    public List<Patient> search() {
        try {
            return connection.getServer().searchPatients(connection.getSession(), null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Patient> search(Object field, String text) {
        try {
            return connection.getServer().searchPatients(connection.getSession(),
                    (PatientSearchOption) field, text);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void show() {
        if (view == null) {
            view = new PatientSearchView(this);
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
