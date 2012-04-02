package org.ece456.proj.gui.accountant;

import java.rmi.RemoteException;

import org.ece456.proj.gui.account.PasswordChangePresenter;
import org.ece456.proj.gui.account.PasswordChangePresenterImpl;
import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.search.doctor.DoctorSearchPresenter;
import org.ece456.proj.gui.shared.table.SelectionListener;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Lawyer;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.shared.Connection;

public class LawyerPresenterImpl implements LawyerPresenter {

    private final Connection connection;

    private Lawyer lawyer;

    private LawyerView view;

    public LawyerPresenterImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void showPasswordChange() {
        PasswordChangePresenter p = new PasswordChangePresenterImpl(connection);
        p.show(UserRole.LEGAL, lawyer.getLawyerId(), lawyer.getPassword());
    }

    @Override
    public void show(Id<Lawyer> id) {
        if (view == null) {
            view = new LawyerView(this);
        }

        try {
            lawyer = connection.getServer().getLawyerById(connection.getSession(), id);

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        view.fillLawyerData(lawyer);

        view.setVisible(true);
    }

    @Override
    public void showDoctorSearch() {
        SearchPresenter<Doctor> p = new DoctorSearchPresenter(connection,
                new SelectionListener<Doctor>() {
                    @Override
                    public AfterAction onSelection(Doctor selected) {
                        // Do something with the Doctor
                        FinancialPresenter p = new FinancialPresenterImpl(connection,
                                selected.getDoctor_id());
                        p.show(selected);
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