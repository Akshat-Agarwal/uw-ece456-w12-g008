package org.ece456.proj.gui.staff;

import org.ece456.proj.gui.shared.table.SimpleTable.Listener;
import org.ece456.proj.orm.objects.Appointment;

public class AppointmentOpener implements Listener<Appointment> {
    private final AppointmentListPresenter presenter;

    public AppointmentOpener(AppointmentListPresenter p) {
        this.presenter = p;
    }

    @Override
    public void onSelection(Appointment selected) {
        AppointmentView view = new AppointmentView(selected, presenter);
        view.setVisible(true);
    }

    public void newAppointment(Appointment app) {
        AppointmentView view = new AppointmentView(app, presenter);
        view.setVisible(true);
    }

    @Override
    public void onCancel() {
        // Do nothing
    }
}
