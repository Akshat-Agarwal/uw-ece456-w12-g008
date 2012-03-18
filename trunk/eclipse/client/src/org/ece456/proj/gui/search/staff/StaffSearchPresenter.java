package org.ece456.proj.gui.search.staff;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.shared.table.SelectionListener;
import org.ece456.proj.gui.shared.table.SelectionListener.AfterAction;
import org.ece456.proj.orm.objects.Staff;
import org.ece456.proj.orm.objects.StaffSearchOption;
import org.ece456.proj.shared.Connection;

public class StaffSearchPresenter implements SearchPresenter<Staff> {

    private StaffSearchView view;
    private final Connection connection;
    private final SelectionListener<Staff> listener;

    public StaffSearchPresenter(Connection connection, SelectionListener<Staff> listener) {
        this.connection = connection;
        this.listener = listener;
    }

    @Override
    public void show() {
        if (view == null) {
            view = new StaffSearchView(this);
        }
        view.setVisible(true);
    }

    @Override
    public List<Staff> search() {
        try {
            return connection.getServer().searchStaff(connection.getSession(), null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Staff> search(Object field, String text) {
        try {
            return connection.getServer().searchStaff(connection.getSession(),
                    (StaffSearchOption) field, text);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void onSelection(Staff selected) {
        AfterAction a = listener.onSelection(selected);
        if (a == AfterAction.CLOSE_DIALOG) {
            view.dispose();
        }
    }

    @Override
    public void onCancel() {
        listener.onCancel();
        view.dispose();
    }
}
