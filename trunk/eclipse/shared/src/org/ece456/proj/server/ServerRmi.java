package org.ece456.proj.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import org.ece456.proj.orm.objects.Accountant;
import org.ece456.proj.orm.objects.Admin;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.AppointmentSearchOption;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.DoctorSearchOption;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Lawyer;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientContact;
import org.ece456.proj.orm.objects.PatientSearchOption;
import org.ece456.proj.orm.objects.Staff;
import org.ece456.proj.orm.objects.StaffSearchOption;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.shared.Session;

public interface ServerRmi extends Remote {

    Session login(UserRole role, Id<?> id, String password) throws RemoteException;

    Patient updatePatientContact(Session session, Id<Patient> id, PatientContact c)
            throws RemoteException;

    Doctor updateDoctor(Session session, Id<Doctor> doctor_id, Doctor doctor)
            throws RemoteException;

    Patient getPatientById(Session session, Id<Patient> id) throws RemoteException;

    Doctor getDoctorById(Session session, Id<Doctor> id) throws RemoteException;

    List<Doctor> getDoctorsById(Session session, Iterable<Id<Doctor>> ids) throws RemoteException;

    List<Appointment> getAppointmentsForPatient(Session session, Id<Patient> id)
            throws RemoteException;

    List<Doctor> getConsultantsForPatient(Session session, Id<Patient> id) throws RemoteException;

    void updatePassword(Session session, UserRole role, Id<?> id, String oldPassword,
            String newPassword) throws RemoteException;

    Admin getAdminById(Session session, Id<Admin> id) throws RemoteException;

    List<Patient> searchPatients(Session session, PatientSearchOption option, String text)
            throws RemoteException;

    List<Doctor> searchDoctors(Session session, DoctorSearchOption option, String text)
            throws RemoteException;

    List<Staff> searchStaff(Session session, StaffSearchOption field, String text)
            throws RemoteException;

    Accountant getAccountantById(Session session, Id<Accountant> id) throws RemoteException;

    Lawyer getLawyerById(Session session, Id<Lawyer> id) throws RemoteException;

    List<Appointment> getAppointmentsForDoctor(Session session, Id<Doctor> doctorId, Date start,
            Date end) throws RemoteException;

    List<Appointment> getAppointmentsForPatient(Session session, Id<Patient> patientId, Date start,
            Date end) throws RemoteException;

    Staff getStaffById(Session session, Id<Staff> id) throws RemoteException;

    List<Doctor> searchDoctorByStaff(Session session, Id<Staff> id, DoctorSearchOption option,
            String text) throws RemoteException;

    List<Patient> searchPatientByStaff(Session session, Id<Staff> id, PatientSearchOption option,
            String text) throws RemoteException;

    Patient createNewPatient(Session session, Patient p) throws RemoteException;

    Boolean createAppointment(Session session, Appointment a) throws RemoteException;

    Patient updatePatient(Session session, Patient p) throws RemoteException;

    List<Patient> searchPatientByDoctor(Session session, Id<Doctor> id, PatientSearchOption option,
            String text) throws RemoteException;

    int searchDoctorIdByPatientId(Session session, Id<Patient> patientId,
            PatientSearchOption option, String text) throws RemoteException;

    void updateNumVisits() throws RemoteException;

    void updateNumVisits(int patientId) throws RemoteException;

    boolean addConsultantForPatient(Session session, Id<Patient> patientId, Doctor consultant)
            throws RemoteException;

    boolean removeConsultantForPatient(Session session, Id<Patient> patientId, Doctor consultant)
            throws RemoteException;

    List<Appointment> searchDoctorAppointments(Session session, Id<Doctor> doctor,
            AppointmentSearchOption option, String text) throws RemoteException;
}
