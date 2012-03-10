package org.ece456.proj.gui.admin;

import java.rmi.RemoteException;

import org.ece456.proj.gui.account.PasswordChangePresenter;
import org.ece456.proj.gui.account.PasswordChangePresenterImpl;
import org.ece456.proj.gui.patient.search.PatientSearchPresenter;
import org.ece456.proj.gui.patient.search.PatientSearchPresenterImpl;
import org.ece456.proj.orm.objects.Admin;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.shared.Connection;

public class AdminPresenterImpl implements AdminPresenter {

    private final Connection connection;

    private Admin admin;

    private AdminView view;

    public AdminPresenterImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void showPasswordChange() {
        PasswordChangePresenter p = new PasswordChangePresenterImpl(connection);
        p.show(UserRole.ADMIN, admin.getAdmin_id(), admin.getPassword());
    }

    @Override
    public void show(Id<Admin> id) {
        if (view == null) {
            view = new AdminView(this);
        }

        try {
            admin = connection.getServer().getAdminById(connection.getSession(), id);

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        view.fillAdminData(admin);

        view.setVisible(true);
    }

    @Override
    public void showPatientSearch() {
        PatientSearchPresenter p = new PatientSearchPresenterImpl(connection);
        p.show();
    }
}
