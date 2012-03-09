package org.ece456.proj.server.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.Sex;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.server.ServerRmi;
import org.ece456.proj.server.authentication.SessionManager;
import org.ece456.proj.shared.Session;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class ServerRmiImpl extends UnicastRemoteObject implements ServerRmi {

    private static final long serialVersionUID = 1L;
    private final Connection dbCon;

    protected ServerRmiImpl(Connection dbCon) throws RemoteException {
        super();
        this.dbCon = dbCon;
    }

    @Override
    public Session login(UserRole role, Id<?> id, String password) throws RemoteException {

        // TODO: check if (role, username, passwordHash) tuple is valid in the DB!
        boolean valid = false;

        try {
            Statement sql = dbCon.createStatement();
            ResultSet result = null;

            switch (role) {
                case PATIENT:
                    result = sql
                            .executeQuery("SELECT patient_id as id, password FROM patient_medical NATURAL JOIN patient_contact");
                    break;
                case DOCTOR:
                    result = sql.executeQuery("SELECT doctor_id as id, password FROM doctor");
                    break;
                case STAFF:
                    result = sql.executeQuery("SELECT staff_id as id, password FROM staff");
                    break;
                case LEGAL:
                    result = sql.executeQuery("SELECT laywer_id as id, password FROM legal");
                    break;
                case ADMIN:
                    result = sql.executeQuery("SELECT admin_id as id, password FROM admin");
                    break;
                case ACCOUNTANT:
                    result = sql
                            .executeQuery("SELECT accountant_id as id, password FROM accountant");
                    break;
            }

            while (result.next()) {
                int resultId = result.getInt("id");
                String resultPassword = result.getString("password");

                if (resultId == id.asInt() && resultPassword.equals(password)) {
                    valid = true;
                    break;
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (valid) {
            System.out.printf("Login attempt from %s %d %s\n", role.toString(), id.asInt(),
                    password);

            return SessionManager.INSTANCE.getNewSession(role, id);
        } else {
            return null;
        }

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

    @Override
    public List<Appointment> getAppointmentsForPatient(Session session, Id<Patient> id)
            throws RemoteException {
        List<Appointment> results = Lists.newArrayList();

        for (int i = 0; i < 100; i++) {
            Appointment a = new Appointment();
            a.setComment("PATIENT SHOULD NOT SEE COMMENT " + i);
            a.setDiagnoses("Diag" + i);
            a.setDoctor_id(Id.<Doctor> of(i));
            a.setLast_modified(new Date());
            a.setLength(i % 30 + 20);
            a.setPatient_id(Id.<Patient> of(i + 1000));
            a.setPrescriptions("presc " + i);
            a.setProcedures("procs" + i);
            a.setStart_time(new Date());
            results.add(a);
        }
        return results;
    }

    private boolean isSessionValid(Session session) {
        return SessionManager.INSTANCE.isValidSession(session);
    }
}
