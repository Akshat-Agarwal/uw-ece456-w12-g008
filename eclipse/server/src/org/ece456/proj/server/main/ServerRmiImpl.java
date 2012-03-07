package org.ece456.proj.server.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.server.ServerRmi;
import org.ece456.proj.server.authentication.SessionManager;
import org.ece456.proj.shared.Session;

public class ServerRmiImpl extends UnicastRemoteObject implements ServerRmi {

    private static final long serialVersionUID = 1L;

    protected ServerRmiImpl() throws RemoteException {
        super();
    }

    @Override
    public Session login(UserRole role, String username, long passwordHash) throws RemoteException {

        // TODO: check if (role, username, passwordHash) tuple is valid in the DB!

        System.out.printf("Login attempt from %s %s %d\n", role.toString(), username, passwordHash);

        return SessionManager.INSTANCE.getNewSession(role, username);
    }

    @Override
    public Patient getPatientById(Id<Patient> id) {

        // TODO actually connect to DB and return results

        Patient p = new Patient();
        p.setPatientId(Id.<Patient> of(1234));
        p.getContact().setName("Homer Simpson");
        p.getContact().setAddress("742 Evergreen Terrace");
        p.getContact().setPhoneNum("555-1234");
        return p;
    }
}
