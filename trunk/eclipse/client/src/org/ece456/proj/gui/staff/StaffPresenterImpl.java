package org.ece456.proj.gui.staff;

import java.rmi.RemoteException;
import java.util.List;

import org.ece456.proj.gui.account.PasswordChangePresenter;
import org.ece456.proj.gui.account.PasswordChangePresenterImpl;
import org.ece456.proj.gui.newPatient.NewPatientPresenter;
import org.ece456.proj.gui.newPatient.NewPatientPresenterImpl;
import org.ece456.proj.gui.shared.table.SelectionListener;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientContact;
import org.ece456.proj.orm.objects.Staff;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.shared.Connection;

/**
 * Patients:
 * <ul>
 * <li>view my past appointments, prescriptions, diagnoses, etc.</li>
 * <li>the records should be confidential (need password of some kind)</li>
 * <li>update my address, phone number, etc.</li>
 * </ul>
 */
public class StaffPresenterImpl implements StaffPresenter {

    private final Connection connection;
    private StaffView view;
    private Staff staff;
    private Patient patient;
    private List<Doctor> doctors;

    public StaffPresenterImpl(Connection connection) {
        this.connection = connection;
    }

    public String[] getDoctors() {
        String[] doctorsNames;
        try {
            doctors = connection.getServer().searchDoctorByStaff(connection.getSession(),
                    staff.getStaffId(), null, null);
            doctorsNames = new String[doctors.size()];
            int i = 0;
            for (Doctor d : doctors) {
                doctorsNames[i] = d.getName();
            }
            return doctorsNames;
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void show(Id<Staff> id) {
        if (view == null) {
            view = new StaffView(this);
        }

        try {

            // Query for patient contact+medical data
            Staff s = connection.getServer().getStaffById(connection.getSession(), id);
            this.staff = s;
            view.fillStaffData(s);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        view.setVisible(true);
    }

    @Override
    public void hide() {
        view.setVisible(false);
    }

    @Override
    public void showNewPatientView() {
        NewPatientPresenter presenter = new NewPatientPresenterImpl(this.connection, this.staff);
        presenter.show(null);
    }

    @Override
    public void savePersonalData(PatientContact patientContact) {
        try {
            connection.getServer().updatePatientContact(connection.getSession(),
                    patient.getPatientId(), patientContact);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() {
        show(staff.getStaffId());
    }

    @Override
    public void showPasswordChange() {
        PasswordChangePresenter p = new PasswordChangePresenterImpl(connection);
        p.show(UserRole.STAFF, staff.getStaffId(), staff.getPassword());
    }

    @Override
    public void showDoctorSearch() {
        SearchDoctorByStaffPresenter p = new SearchDoctorByStaffPresenter(connection,
                new SelectionListener<Doctor>() {
                    @Override
                    public AfterAction onSelection(Doctor selected) {
                        AppointmentListPresenter p = new AppointmentListPresenterImpl(connection,
                                null, selected);
                        p.show(selected);
                        return AfterAction.DO_NOTHING;
                    }

                    @Override
                    public void onCancel() {
                        // do nothing
                    }
                }, this.staff);
        p.show();
    }

    @Override
    public void showPatientSearch() {
        SearchPatientByStaffPresenter p = new SearchPatientByStaffPresenter(connection,
                new SelectionListener<Patient>() {
                    @Override
                    public AfterAction onSelection(Patient selected) {
                        AppointmentListPresenter p = new AppointmentListPresenterImpl(connection,
                                selected, null);
                        p.show(selected);
                        return AfterAction.DO_NOTHING;
                    }

                    @Override
                    public void onCancel() {
                        // do nothing
                    }
                }, this.staff);
        p.show();
    }

    @Override
    public void showPatientEditSearch() {
        SearchPatientByStaffPresenter p = new SearchPatientByStaffPresenter(connection,
                new SelectionListener<Patient>() {
                    @Override
                    public AfterAction onSelection(Patient selected) {
                        NewPatientPresenter p = new NewPatientPresenterImpl(connection, staff);
                        p.show(selected);
                        return AfterAction.DO_NOTHING;
                    }

                    @Override
                    public void onCancel() {
                        // do nothing
                    }
                }, this.staff);
        p.show();
    }
}
