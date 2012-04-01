package org.ece456.proj.gui.staff;

import org.ece456.proj.gui.appointment.AppointmentTableColumns;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.table.SimpleTableModel;
import org.ece456.proj.orm.objects.Appointment;

import com.google.common.collect.ImmutableList;

/**
 * For Patient use only: lots of info is hidden from patients.
 * 
 * start time doctor_id length procedures prescriptions diagnoses
 */
public class AppointmentListTable extends SimpleTable<Appointment> {
    private static final long serialVersionUID = 1L;

    public AppointmentListTable(AppointmentListPresenter p) {
        super(new AppointmentOpener(p), true, false);
    }

    @Override
    protected SimpleTableModel<Appointment> initModel() {
        return SimpleTableModel.create(ImmutableList.of(AppointmentTableColumns.START_TIME,
                AppointmentTableColumns.DURATION, AppointmentTableColumns.DOCTOR,
                AppointmentTableColumns.PATIENT, AppointmentTableColumns.DIAGNOSES,
                AppointmentTableColumns.PRESCRIPTIONS, AppointmentTableColumns.PROCEDURES));
    }
}
