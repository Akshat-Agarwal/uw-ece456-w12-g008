package org.ece456.proj.gui.patient;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientContact;
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

    public PatientPresenterImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void updatePatientContact(Id<Patient> id, PatientContact contact) {
        // TODO call server
    }

    @Override
    public void show(Patient patient) {
        if (view == null) {
            view = new PatientView(this);
        }
        view.fillPatientData(patient);
        view.setVisible(true);
    }

    @Override
    public void hide() {
        view.setVisible(false);
    }
}
