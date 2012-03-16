package org.ece456.proj.gui.accountant;

import org.ece456.proj.gui.appointment.AppointmentTable;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.table.SimpleTableModel;
import org.ece456.proj.orm.objects.Appointment;

import com.google.common.collect.ImmutableList;

public class DoctorsAppointmentTable extends SimpleTable<Appointment> {
    private static final long serialVersionUID = 1L;

    @Override
    protected SimpleTableModel<Appointment> initModel() {
        return SimpleTableModel.create(ImmutableList.of(AppointmentTable.START_TIME,
                AppointmentTable.DURATION, AppointmentTable.DIAGNOSES,
                AppointmentTable.PRESCRIPTIONS, AppointmentTable.PROCEDURES));
    }
}
