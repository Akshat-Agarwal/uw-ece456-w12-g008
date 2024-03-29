package org.ece456.proj.gui.doctor;

import java.rmi.RemoteException;

import org.ece456.proj.gui.account.PasswordChangePresenter;
import org.ece456.proj.gui.account.PasswordChangePresenterImpl;
import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.search.appointments.DoctorAppointmentSearchPresenter;
import org.ece456.proj.gui.search.patient.PatientSearchForDoctorPresenter;
import org.ece456.proj.gui.search.patient.PatientSearchPresenter;
import org.ece456.proj.gui.shared.table.SelectionListener;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.shared.Connection;

public class DoctorPresenterImpl implements DoctorPresenter {

    private final Connection connection;
    private DoctorView view;
    private Doctor doctor;

    public DoctorPresenterImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void show(Id<Doctor> id) {
        if (view == null) {
            view = new DoctorView(this);
        }

        try {
            // Query for patient contact+medical data
            Doctor d = connection.getServer().getDoctorById(connection.getSession(), id);

            this.doctor = d;

            view.fillDoctorData(d);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        view.setVisible(true);
    }

    @Override
    public void showAllPatientSearch() {
        SearchPresenter<Patient> p = new PatientSearchPresenter(connection,
                new SelectionListener<Patient>() {
                    @Override
                    public AfterAction onSelection(Patient selected) {
                        return AfterAction.DO_NOTHING;
                    }

                    @Override
                    public void onCancel() {
                        // do nothing
                    }
                });

        p.show();
    }

    @Override
    public void showMyPatientSearch() {
        SearchPresenter<Patient> p = new PatientSearchForDoctorPresenter(connection,
                new SelectionListener<Patient>() {
                    @Override
                    public AfterAction onSelection(Patient selected) {
                        PatientDoctorPresenter p = new PatientDoctorPresenterImpl(connection);
                        p.show(selected);
                        return AfterAction.DO_NOTHING;
                    }

                    @Override
                    public void onCancel() {
                        // do nothing
                    }
                }, doctor.getDoctor_id());
        p.show();
    }

    @Override
    public void hide() {
        view.setVisible(false);
    }

    @Override
    public void savePersonalData(Doctor doctor) {
        try {
            connection.getServer().updateDoctor(connection.getSession(), doctor.getDoctor_id(),
                    doctor);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() {
        show(doctor.getDoctor_id());
    }

    @Override
    public void showPasswordChange() {
        PasswordChangePresenter p = new PasswordChangePresenterImpl(connection);
        p.show(UserRole.DOCTOR, doctor.getDoctor_id(), doctor.getPassword());
    }

    @Override
    public void showAppointmentsSearch() {
        SearchPresenter<Appointment> p = new DoctorAppointmentSearchPresenter(connection,
                new SelectionListener<Appointment>() {
                    @Override
                    public AfterAction onSelection(Appointment selected) {
                        DoctorPatientAppointmentView v = new DoctorPatientAppointmentView(selected,
                                new PatientDoctorPresenterImpl(connection));
                        v.setVisible(true);
                        return AfterAction.DO_NOTHING;
                    }

                    @Override
                    public void onCancel() {
                        // do nothing
                    }
                }, doctor.getDoctor_id());
        p.show();
    }
}
