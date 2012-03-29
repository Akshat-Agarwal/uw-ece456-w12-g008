package org.ece456.proj.gui.accountant;

import java.util.Date;
import java.util.List;

import org.ece456.proj.gui.appointment.AppointmentView.AppointmentPresenter;
import org.ece456.proj.gui.appointment.DoctorsAppointmentTable;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;

public class DoctorFinancialView extends AbstractFinancialView {
    private static final long serialVersionUID = 1L;

    private final DoctorFinancialPresenter presenter;

    public DoctorFinancialView(DoctorFinancialPresenter presenter) {
        super(presenter.getAppointmentPresenter());
        this.presenter = presenter;
        setTitle("View Doctor's Appointments");
    }

    public void fillData(Doctor doctor) {
        textFieldId.setText(doctor.getDoctor_id().toString());
        textFieldName.setText(doctor.getName());
    }

    public void fillAppointments(List<Appointment> apps) {
        table.update(apps);
    }

    @Override
    public void onSearch(Date start, Date end) {
        presenter.searchAppointments(start, end);
    }

    @Override
    SimpleTable<Appointment> createAppointmentsTable(AppointmentPresenter p) {
        return new DoctorsAppointmentTable(p);
    }
}
