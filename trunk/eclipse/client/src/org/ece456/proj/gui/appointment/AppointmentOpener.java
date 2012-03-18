package org.ece456.proj.gui.appointment;

import org.ece456.proj.gui.shared.table.SimpleTable.Listener;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.UserRole;

public class AppointmentOpener implements Listener<Appointment> {

    private final boolean canViewComments;

    public AppointmentOpener(UserRole role) {
        this.canViewComments = (role != UserRole.PATIENT);
    }

    @Override
    public void onSelection(Appointment selected) {
        AppointmentView view = new AppointmentView(selected, canViewComments);
        view.setVisible(true);
    }

    @Override
    public void onCancel() {
        // Do nothing
    }
}
