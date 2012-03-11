package org.ece456.proj.gui.admin;

import java.rmi.RemoteException;

import org.ece456.proj.gui.account.PasswordChangePresenter;
import org.ece456.proj.gui.account.PasswordChangePresenterImpl;
import org.ece456.proj.gui.patient.PatientPresenter;
import org.ece456.proj.gui.patient.PatientPresenterImpl;
import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.search.doctor.DoctorSearchPresenter;
import org.ece456.proj.gui.search.patient.PatientSearchPresenter;
import org.ece456.proj.gui.shared.table.SelectionListener;
import org.ece456.proj.orm.objects.Admin;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
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
        SearchPresenter<Patient> p = new PatientSearchPresenter(connection,
                new SelectionListener<Patient>() {
                    @Override
                    public AfterAction onSelection(Patient selected) {
                        // Show patient view
                        PatientPresenter presenter = new PatientPresenterImpl(connection);
                        presenter.show(selected.getPatientId());
                        return AfterAction.DO_NOTHING;
                    }

                    @Override
                    public void onCancel() {
                        // do nothing
                    }
                });
        p.show();
    }

    @Override
    public void showDoctorSearch() {
        SearchPresenter<Doctor> p = new DoctorSearchPresenter(connection,
                new SelectionListener<Doctor>() {
                    @Override
                    public AfterAction onSelection(Doctor selected) {
                        // TODO Show Doctor view
                        System.out.println(selected.getName());
                        return AfterAction.DO_NOTHING;
                    }

                    @Override
                    public void onCancel() {
                        // do nothing
                    }
                });
        p.show();
    }
}
