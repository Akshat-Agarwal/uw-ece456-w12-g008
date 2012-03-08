package org.ece456.proj.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.shared.Session;

public interface ServerRmi extends Remote {

    Session login(UserRole role, String username, long passwordHash) throws RemoteException;

    Patient getPatientById(Session session, Id<Patient> id) throws RemoteException;
}
