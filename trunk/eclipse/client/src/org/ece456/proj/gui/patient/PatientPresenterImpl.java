package org.ece456.proj.gui.patient;

import java.rmi.RemoteException;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientContact;
import org.ece456.proj.shared.Connection;

public class PatientPresenterImpl implements PatientPresenter {

    private final Connection connection;

    public PatientPresenterImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Patient getPatient(Id<Patient> id) {
        try {
            return connection.getServer().getPatientById(connection.getSession(), id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatePatientContact(Id<Patient> id, PatientContact contact) {
        // TODO call server
    }

    @Override
    public String getUsername() {
        return connection.getSession().getUsername();
    }
}
