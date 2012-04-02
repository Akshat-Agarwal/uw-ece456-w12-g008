package org.ece456.proj.gui.newPatient;

import java.rmi.RemoteException;
import java.util.List;

import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.Staff;
import org.ece456.proj.shared.Connection;

/**
 * Patients:
 * <ul>
 * <li>view my past appointments, prescriptions, diagnoses, etc.</li>
 * <li>the records should be confidential (need password of some kind)</li>
 * <li>update my address, phone number, etc.</li>
 * </ul>
 */
public class NewPatientPresenterImpl implements NewPatientPresenter {

    private final Connection connection;
    private NewPatientView view;
    private final Staff staff;
    private List<Doctor> doctors;

    public NewPatientPresenterImpl(Connection connection, Staff staff) {
        this.connection = connection;
        this.staff = staff;
    }

    public List<Doctor> getDoctors() {
        try {
            doctors = connection.getServer().searchDoctorByStaff(connection.getSession(),
                    staff.getStaffId(), null, null);
            return doctors;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void show(Patient patient) {
        if (view == null) {
            view = new NewPatientView(this);
        }
        view.fillData(getDoctors(), patient);
        view.setVisible(true);
    }

    @Override
    public void createPatient() {
        Patient p = view.getPatientInfo();

        Patient exist = null;
        try {
            exist = connection.getServer()
                    .getPatientById(connection.getSession(), p.getPatientId());
            if (exist != null)
                return;
            connection.getServer().createNewPatient(connection.getSession(), p);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePatient() {
        Patient p = view.getPatientInfo();
        try {
            connection.getServer().updatePatient(connection.getSession(), p);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
