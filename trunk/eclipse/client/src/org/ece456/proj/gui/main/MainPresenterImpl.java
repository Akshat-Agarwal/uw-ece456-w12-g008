package org.ece456.proj.gui.main;

import org.ece456.proj.gui.accountant.AccountantPresenter;
import org.ece456.proj.gui.accountant.AccountantPresenterImpl;
import org.ece456.proj.gui.admin.AdminPresenter;
import org.ece456.proj.gui.admin.AdminPresenterImpl;
import org.ece456.proj.gui.doctor.DoctorPresenter;
import org.ece456.proj.gui.doctor.DoctorPresenterImpl;
import org.ece456.proj.gui.patient.PatientPresenter;
import org.ece456.proj.gui.patient.PatientPresenterImpl;
import org.ece456.proj.gui.staff.StaffPresenter;
import org.ece456.proj.gui.staff.StaffPresenterImpl;
import org.ece456.proj.orm.objects.Accountant;
import org.ece456.proj.orm.objects.Admin;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.Staff;
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
            case ADMIN:
                showAdminView(connection, id);
                break;
            case ACCOUNTANT:
                showAccountant(connection, id);
                break;
            case DOCTOR:
                showDoctorView(connection, id);
                break;            case STAFF:
                showStaffView(connection, id);
                break;            default:
                return "That view is not supported yet";
        }

        return "";
    }

    private void showAccountant(Connection connection, int id) {
        AccountantPresenter presenter = new AccountantPresenterImpl(connection);
        presenter.show(Id.<Accountant> of(id));
    }

    private void showPatientView(Connection connection, int id) {
        PatientPresenter presenter = new PatientPresenterImpl(connection);
        presenter.show(Id.<Patient> of(id));
    }

    private void showAdminView(Connection connection, int id) {
        AdminPresenter presenter = new AdminPresenterImpl(connection);
        presenter.show(Id.<Admin> of(id));
    }

    private void showDoctorView(Connection connection, int id) {
        DoctorPresenter presenter = new DoctorPresenterImpl(connection);
        presenter.show(Id.<Doctor> of(id));
    }

    private void showStaffView(Connection connection, int id) {
        StaffPresenter presenter = new StaffPresenterImpl(connection);
        presenter.show(Id.<Staff> of(id));
    }}
