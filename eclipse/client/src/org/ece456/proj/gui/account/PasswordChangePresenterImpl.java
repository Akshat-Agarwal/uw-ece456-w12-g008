package org.ece456.proj.gui.account;

import java.rmi.RemoteException;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.shared.Connection;

public class PasswordChangePresenterImpl implements PasswordChangePresenter {

    private final Connection connection;

    private PasswordChangeView view;

    private UserRole role;
    private Id<?> id;
    private String oldPassword;

    public PasswordChangePresenterImpl(Connection connection) {
        this.connection = connection;

    }

    @Override
    public void show(UserRole role, Id<?> id, String password) {
        this.role = role;
        this.id = id;
        this.oldPassword = password;

        if (view == null) {
            view = new PasswordChangeView(this);
        }

        view.fillAccountData(role, id, password);
        view.setVisible(true);
    }

    @Override
    public void updatePassword(String newPassword) {
        try {
            connection.getServer().updatePassword(connection.getSession(), role, id, oldPassword,
                    newPassword);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
