package org.ece456.proj.gui.patient;

import java.rmi.RemoteException;
import java.util.List;

import org.ece456.proj.gui.account.PasswordChangePresenter;
import org.ece456.proj.gui.account.PasswordChangePresenterImpl;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientContact;
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
public class PatientPresenterImpl implements PatientPresenter {

    private final Connection connection;
    private PatientView view;
    private Patient patient;

    public PatientPresenterImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void show(Id<Patient> id) {
        if (view == null) {
            view = new PatientView(this);
        }

        try {

            // Query for patient contact+medical data
            Patient p = connection.getServer().getPatientById(connection.getSession(), id);

            this.patient = p;

            // Query for appointments for the patient
            List<Appointment> apps = connection.getServer().getAppointmentsForPatient(
                    connection.getSession(), p.getPatientId());

            // Query for consultants for the patient
            List<Doctor> consultants = connection.getServer().getConsultantsForPatient(
                    connection.getSession(), p.getPatientId());

            p.getMedical().setConsultants(consultants);

            view.fillPatientData(p, apps);
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
        show(patient.getPatientId());
    }

    @Override
    public void showPasswordChange() {
        PasswordChangePresenter p = new PasswordChangePresenterImpl(connection);
        p.show(UserRole.PATIENT, patient.getPatientId(), patient.getContact().getPassword());
    }
}
