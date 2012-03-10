package org.ece456.proj.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.shared.Session;

public interface ServerRmi extends Remote {

    Session login(UserRole role, Id<?> id, String password) throws RemoteException;

    Patient getPatientById(Session session, Id<Patient> id) throws RemoteException;

    Doctor getDoctorById(Session session, Id<Doctor> id) throws RemoteException;

    List<Doctor> getDoctorsById(Session session, Iterable<Id<Doctor>> ids) throws RemoteException;

    List<Appointment> getAppointmentsForPatient(Session session, Id<Patient> id)
            throws RemoteException;
}
