package org.ece456.proj.gui.appointment;

import org.ece456.proj.gui.appointment.AppointmentView.AppointmentPresenter;
import org.ece456.proj.gui.shared.table.SimpleTable.Listener;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.UserRole;

public class AppointmentOpener implements Listener<Appointment> {

    private final UserRole role;
    private final AppointmentPresenter presenter;

    public AppointmentOpener(UserRole role, AppointmentPresenter p) {
        this.role = role;
        this.presenter = p;
    }

    @Override
    public void onSelection(Appointment selected) {
        assert presenter != null;
        AppointmentView view = new AppointmentView(selected, role, presenter);
        view.setVisible(true);
    }

    @Override
    public void onCancel() {
        // Do nothing
    }
}
