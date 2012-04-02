package org.ece456.proj.gui.search.appointments;

import org.ece456.proj.gui.appointment.AppointmentTableColumns;
import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.search.SearchView;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.AppointmentSearchOption;

import com.google.common.collect.ImmutableList;

public class DoctorAppointmentSearchView extends SearchView<Appointment> {
    private static final long serialVersionUID = 1L;

    public DoctorAppointmentSearchView(SearchPresenter<Appointment> presenter) {
        super("Search for your appointment", AppointmentSearchOption.values(), ImmutableList.of(
                AppointmentTableColumns.START_TIME, AppointmentTableColumns.PATIENT_ID,
                AppointmentTableColumns.DURATION, AppointmentTableColumns.DIAGNOSES,
                AppointmentTableColumns.PRESCRIPTIONS, AppointmentTableColumns.PROCEDURES),
                presenter);
    }

}
