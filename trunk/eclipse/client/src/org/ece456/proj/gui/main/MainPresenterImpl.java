package org.ece456.proj.gui.main;

import org.ece456.proj.gui.patient.PatientPresenter;
import org.ece456.proj.gui.patient.PatientPresenterImpl;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.shared.Connection;

public class MainPresenterImpl implements MainPresenter {

    @Override
    public String login(String host, UserRole role, int id, char[] password) {
        RmiClient client = new RmiClient();

        Connection connection = client.connect(host, role, id, String.valueOf(password));

        if (connection == null || !connection.getSession().isValid()) {
            // failed to connect
            return "Failed to connect to server; check host, username and password";
        }

        switch (role) {
            case PATIENT:
                showPatientView(connection, id);
                break;
            default:
                return "That view is not supported yet";
        }

        return "";
    }

    private void showPatientView(Connection connection, int id) {
        PatientPresenter presenter = new PatientPresenterImpl(connection);
        presenter.show(Id.<Patient> of(id));
    }
}
