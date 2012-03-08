package org.ece456.proj.gui.main;

import java.rmi.RemoteException;

import org.ece456.proj.gui.patient.PatientPresenter;
import org.ece456.proj.gui.patient.PatientPresenterImpl;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.shared.Connection;

public class MainPresenterImpl implements MainPresenter {

    @Override
    public String login(String host, UserRole role, String username, char[] password) {
        RmiClient client = new RmiClient();

        Connection connection = client.connect(host, role, username, String.valueOf(password));

        if (connection == null) {
            // failed to connect
            return "Failed to connect to server; check host, username and password";
        }

        switch (role) {
            case PATIENT:
                showPatientView(connection);
                break;
            default:
                return "That view is not supported yet";
        }

        return "";
    }

    private void showPatientView(Connection connection) {
        PatientPresenter presenter = new PatientPresenterImpl(connection);
        try {
            Patient p = connection.getServer().getPatientById(connection.getSession(),
                    Id.<Patient> of(0));
            presenter.show(p);

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
