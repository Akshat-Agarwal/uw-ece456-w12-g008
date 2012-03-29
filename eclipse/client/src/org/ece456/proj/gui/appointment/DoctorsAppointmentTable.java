package org.ece456.proj.gui.appointment;

import org.ece456.proj.gui.appointment.AppointmentView.AppointmentPresenter;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.table.SimpleTableModel;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.UserRole;

import com.google.common.collect.ImmutableList;

public class DoctorsAppointmentTable extends SimpleTable<Appointment> {
    private static final long serialVersionUID = 1L;

    public DoctorsAppointmentTable(AppointmentPresenter p) {
        super(new AppointmentOpener(UserRole.DOCTOR, p), true, false);
    }

    @Override
    protected SimpleTableModel<Appointment> initModel() {
        return SimpleTableModel.create(ImmutableList.of(AppointmentTableColumns.START_TIME,
                AppointmentTableColumns.PATIENT, AppointmentTableColumns.DURATION,
                AppointmentTableColumns.DIAGNOSES, AppointmentTableColumns.PRESCRIPTIONS,
                AppointmentTableColumns.PROCEDURES));
    }
}
