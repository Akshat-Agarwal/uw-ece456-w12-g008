package org.ece456.proj.gui.appointment;

import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.table.SimpleTableModel;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.UserRole;

import com.google.common.collect.ImmutableList;

/**
 * For Patient use only: lots of info is hidden from patients.
 * 
 * start time doctor_id length procedures prescriptions diagnoses
 */
public class PatientsAppointmentTable extends SimpleTable<Appointment> {
    private static final long serialVersionUID = 1L;

    public PatientsAppointmentTable() {
        super(new AppointmentOpener(UserRole.PATIENT, null), true, false);
    }

    @Override
    protected SimpleTableModel<Appointment> initModel() {
        return SimpleTableModel.create(ImmutableList.of(AppointmentTableColumns.START_TIME,
                AppointmentTableColumns.DURATION, AppointmentTableColumns.DOCTOR,
                AppointmentTableColumns.DIAGNOSES, AppointmentTableColumns.PRESCRIPTIONS,
                AppointmentTableColumns.PROCEDURES));
    }
}
