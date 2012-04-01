package org.ece456.proj.gui.doctor;

import org.ece456.proj.gui.shared.table.SimpleTable.Listener;
import org.ece456.proj.orm.objects.Appointment;

public class DoctorPatientAppointmentOpener implements Listener<Appointment> {

    private final PatientDoctorPresenter presenter;

    public DoctorPatientAppointmentOpener(PatientDoctorPresenter p) {
        this.presenter = p;
    }

    @Override
    public void onSelection(Appointment selected) {
        DoctorPatientAppointmentView view = new DoctorPatientAppointmentView(selected, presenter);
        view.setVisible(true);
    }

    @Override
    public void onCancel() {
        // Do nothing
    }
}
