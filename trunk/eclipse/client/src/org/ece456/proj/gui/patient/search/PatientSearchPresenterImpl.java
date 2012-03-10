package org.ece456.proj.gui.patient.search;

import java.util.List;

import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.shared.Connection;

public class PatientSearchPresenterImpl implements PatientSearchPresenter {

    private final Connection connection;
    private PatientSearchView view;

    public PatientSearchPresenterImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Patient> search(PatientSearchOption field, String text) {
        return null;
    }

    @Override
    public void show() {
        if (view == null) {
            view = new PatientSearchView(this);
        }

        view.setVisible(true);
    }
}
