package org.ece456.proj.gui.accountant;

import org.ece456.proj.gui.appointment.AppointmentOpener;
import org.ece456.proj.gui.appointment.AppointmentTableColumns;
import org.ece456.proj.gui.appointment.AppointmentView.AppointmentPresenter;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.table.SimpleTableModel;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.UserRole;

import com.google.common.collect.ImmutableList;

public class FinancialAppointmentTable extends SimpleTable<Appointment> {
    private static final long serialVersionUID = 1L;

    public FinancialAppointmentTable(AppointmentPresenter p) {
        super(new AppointmentOpener(UserRole.ACCOUNTANT, p), true, false);
    }

    @Override
    protected SimpleTableModel<Appointment> initModel() {
        return SimpleTableModel.create(ImmutableList.of(AppointmentTableColumns.START_TIME,
                AppointmentTableColumns.PATIENT_ID, AppointmentTableColumns.DURATION,
                AppointmentTableColumns.DIAGNOSES, AppointmentTableColumns.PRESCRIPTIONS,
                AppointmentTableColumns.PROCEDURES));
    }
}
