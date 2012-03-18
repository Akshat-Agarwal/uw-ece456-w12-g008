package org.ece456.proj.gui.accountant;

import java.util.Date;
import java.util.List;

import org.ece456.proj.gui.appointment.PatientsAppointmentTable;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Patient;

public class PatientFinancialView extends AbstractFinancialView {
    private static final long serialVersionUID = 1L;

    private final PatientFinancialPresenter presenter;

    public PatientFinancialView(PatientFinancialPresenter presenter) {
        this.presenter = presenter;
        setTitle("View Patient's Appointments");
    }

    public void fillData(Patient patient) {
        textFieldId.setText(patient.getPatientId().toString());
        textFieldName.setText(patient.getName());
    }

    public void fillAppointments(List<Appointment> apps) {
        table.update(apps);
    }

    @Override
    public void onSearch(Date start, Date end) {
        presenter.searchAppointments(start, end);
    }

    @Override
    SimpleTable<Appointment> createAppointmentsTable() {
        return new PatientsAppointmentTable();
    }
}
