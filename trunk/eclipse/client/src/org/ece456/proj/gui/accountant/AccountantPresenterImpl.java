package org.ece456.proj.gui.accountant;

import java.rmi.RemoteException;

import org.ece456.proj.gui.account.PasswordChangePresenter;
import org.ece456.proj.gui.account.PasswordChangePresenterImpl;
import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.search.doctor.DoctorSearchPresenter;
import org.ece456.proj.gui.shared.table.SelectionListener;
import org.ece456.proj.orm.objects.Accountant;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.shared.Connection;

public class AccountantPresenterImpl implements AccountantPresenter {

    private final Connection connection;

    private Accountant accountant;

    private AccountantView view;

    public AccountantPresenterImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void showPasswordChange() {
        PasswordChangePresenter p = new PasswordChangePresenterImpl(connection);
        p.show(UserRole.ACCOUNTANT, accountant.getFinanceId(), accountant.getPassword());
    }

    @Override
    public void show(int id) {
        if (view == null) {
            view = new AccountantView(this);
        }

        try {
            accountant = connection.getServer().getAccountantById(connection.getSession(),
                    Id.<Accountant> of(id));

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        view.fillData(accountant.getName(), accountant.getFinanceId().asInt());

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
                                selected.getDoctor_id(), UserRole.ACCOUNTANT);
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
