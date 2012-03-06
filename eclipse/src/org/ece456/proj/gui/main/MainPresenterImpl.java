package org.ece456.proj.gui.main;

import javax.swing.JDialog;

import org.ece456.proj.UserRole;
import org.ece456.proj.gui.patient.PatientPresenter;
import org.ece456.proj.gui.patient.PatientPresenterImpl;
import org.ece456.proj.gui.patient.PatientView;
import org.ece456.proj.orm.query.QueryManager;

public class MainPresenterImpl implements MainPresenter {
    public MainPresenterImpl() {

    }

    @Override
    public void login(UserRole role, String username, char[] password) {
        String password_copy = String.valueOf(password);

        // clear password
        for (int i = 0; i < password.length; i++) {
            password[i] = 0;
        }

        System.out.printf("role: %s  username: %s  password: %s\n", role, username, password_copy);

        switch (role) {
            case PATIENT:
                showPatientView(username);
                break;
            default:
                throw new EnumConstantNotPresentException(UserRole.class, String.valueOf(role));
        }
    }

    private void showPatientView(String username) {
        QueryManager query = QueryManager.connect(UserRole.PATIENT, username, 0L);
        PatientPresenter presenter = new PatientPresenterImpl(query, username);
        JDialog view = new PatientView(presenter);
        view.setVisible(true);
    }
}
