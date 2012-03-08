package org.ece456.proj.server.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.Sex;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.server.ServerRmi;
import org.ece456.proj.server.authentication.SessionManager;
import org.ece456.proj.shared.Session;

import com.google.common.collect.ImmutableList;

public class ServerRmiImpl extends UnicastRemoteObject implements ServerRmi {

    private static final long serialVersionUID = 1L;

    protected ServerRmiImpl() throws RemoteException {
        super();
    }

    @Override
    public Session login(UserRole role, Id<?> id, String password) throws RemoteException {

        // TODO: check if (role, username, passwordHash) tuple is valid in the DB!

        System.out.printf("Login attempt from %s %d %s\n", role.toString(), id.asInt(), password);

        return SessionManager.INSTANCE.getNewSession(role, id);
    }

    @Override
    public Patient getPatientById(Session session, Id<Patient> id) {
        if (!isSessionValid(session)) {
            return null;
        }

        // A patient can only view his own records
        if (session.getRole() == UserRole.PATIENT) {
            // TODO uncomment this later when we have actual user database

            /*
             * if (!id.equals(session.getId())) { return null; }
             */
        }

        // TODO actually connect to DB and return results

        Patient p = new Patient();
        p.setPatientId(Id.<Patient> of(1234));
        p.getContact().setName("Homer Simpson");
        p.getContact().setAddress("742 Evergreen Terrace");
        p.getContact().setPhoneNum("555-1234");

        p.getMedical().setCurrentHealth("Obese");
        p.getMedical().setDefaultDoctor(Id.<Doctor> of(1234));
        p.getMedical().setHealthCardNumber("Ontario-12345");
        p.getMedical().setSin(123456789);
        p.getMedical().setSex(Sex.FEMALE);
        p.getMedical().setNumVisits(100);
        p.getMedical()
                .setConsultants(
                        ImmutableList.of(Id.<Doctor> of(1), Id.<Doctor> of(2), Id.<Doctor> of(3),
                                Id.<Doctor> of(4), Id.<Doctor> of(5), Id.<Doctor> of(6),
                                Id.<Doctor> of(7)));
        return p;
    }

    private boolean isSessionValid(Session session) {
        return SessionManager.INSTANCE.isValidSession(session);
    }
}
