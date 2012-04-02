package org.ece456.proj.gui.doctor;

import org.ece456.proj.gui.appointment.AppointmentTableColumns;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.table.SimpleTableModel;
import org.ece456.proj.orm.objects.Appointment;

import com.google.common.collect.ImmutableList;

public class DoctorPatientsAppointmentTable extends SimpleTable<Appointment> {
    private static final long serialVersionUID = 1L;

    public DoctorPatientsAppointmentTable(PatientDoctorPresenter p) {
        super(new DoctorPatientAppointmentOpener(p), true, false);
    }

    @Override
    protected SimpleTableModel<Appointment> initModel() {
        return SimpleTableModel.create(ImmutableList.of(AppointmentTableColumns.START_TIME,
                AppointmentTableColumns.DURATION, AppointmentTableColumns.DOCTOR,
                AppointmentTableColumns.DIAGNOSES, AppointmentTableColumns.PRESCRIPTIONS,
                AppointmentTableColumns.PROCEDURES));
    }
}
