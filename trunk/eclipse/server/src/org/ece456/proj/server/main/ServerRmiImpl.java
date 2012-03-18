package org.ece456.proj.server.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import org.ece456.proj.orm.objects.Accountant;
import org.ece456.proj.orm.objects.Admin;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.DoctorSearchOption;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientContact;
import org.ece456.proj.orm.objects.PatientSearchOption;
import org.ece456.proj.orm.objects.Sex;
import org.ece456.proj.orm.objects.Staff;
import org.ece456.proj.orm.objects.StaffSearchOption;
import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.server.ServerRmi;
import org.ece456.proj.server.authentication.SessionManager;
import org.ece456.proj.shared.Session;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ServerRmiImpl extends UnicastRemoteObject implements ServerRmi {

    private static final long serialVersionUID = 1L;
    private Connection dbCon;

    protected ServerRmiImpl() throws RemoteException {
        super();
    }

    private Connection getConnection() {
        try {
            if (dbCon == null || dbCon.isClosed()) {
                dbCon = DriverManager.getConnection("jdbc:mysql://localhost/hospital?"
                        + "user=root");
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return dbCon;
    }

    @Override
    public Session login(UserRole role, Id<?> id, String password) throws RemoteException {

        boolean valid = false;

        try {
            ResultSet result = null;

            String query = "SELECT ";

            switch (role) {
                case PATIENT:
                    query += "patient_id as id, password FROM patient_medical NATURAL JOIN patient_contact WHERE patient_id = ?;";
                    break;
                case DOCTOR:
                    query += "doctor_id as id, password FROM doctor WHERE doctor_id = ?;";
                    break;
                case STAFF:
                    query += "staff_id as id, password FROM staff WHERE staff_id = ?;";
                    break;
                case LEGAL:
                    query += "lawyer_id as id, password FROM legal WHERE lawyer_id = ?;";
                    break;
                case ADMIN:
                    query += "admin_id as id, password FROM admin WHERE admin_id = ?;";
                    break;
                case ACCOUNTANT:
                    query += "finance_id as id, password FROM financial WHERE finance_id = ?;";
                    break;
                default:
                    throw new EnumConstantNotPresentException(UserRole.class, String.valueOf(role));
            }

            PreparedStatement sql = getConnection().prepareStatement(query);
            sql.setInt(1, id.asInt());

            System.out.println(sql);
            result = sql.executeQuery();

            while (result.next()) {
                int resultId = result.getInt("id");
                String resultPassword = result.getString("password");

                if (resultId == id.asInt() && resultPassword.equals(password)) {
                    valid = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (valid) {
            System.out.printf("Login attempt from %s %d %s\n", role.toString(), id.asInt(),
                    password);

            return SessionManager.INSTANCE.getNewSession(role, id);
        } else {
            return Session.INVALID_SESSION;
        }
    }

    @Override
    public Patient getPatientById(Session session, Id<Patient> id) {
        if (!isSessionValid(session)) {
            return null;
        }

        // A patient can only view his own records
        if (session.getRole() == UserRole.PATIENT) {
            if (!id.equals(session.getId())) {
                // Trying to access someone else's patient records - deny access
                return null;
            }
        }

        // Invalid ID check - don't bother running the query
        if (Id.invalidId().equals(id)) {
            return null;
        }

        // TODO Insert other constraints here

        try {
            String query = "SELECT * FROM patient_medical" + " NATURAL JOIN patient_contact"
                    + " INNER JOIN doctor ON patient_medical.default_doctor_id = doctor.doctor_id"
                    + " WHERE patient_id = ?;";

            PreparedStatement sql = getConnection().prepareStatement(query);
            sql.setInt(1, id.asInt());

            System.out.println(sql);
            ResultSet result = sql.executeQuery();

            // Forward one result. If it returns false, the result set is empty.
            if (!result.next()) {
                return null;
            }

            Patient p = new Patient();
            p.setPatientId(id);
            p.getContact().setName(result.getString("patient_contact.name"));
            p.getContact().setAddress(result.getString("address"));
            p.getContact().setPhoneNum(result.getString("phone_num"));
            p.getContact().setPassword(result.getString("password"));

            p.getMedical().setCurrentHealth(result.getString("current_health"));

            // Default doctor
            Doctor doctor = new Doctor();
            doctor.setName(result.getString("doctor.name"));
            p.getMedical().setDefaultDoctor(doctor);

            p.getMedical().setHealthCardNumber(result.getString("health_card_num"));
            p.getMedical().setSin(result.getInt("sin"));
            if (result.getString("sex").equals("male")) {
                p.getMedical().setSex(Sex.MALE);
            } else {
                p.getMedical().setSex(Sex.FEMALE);
            }

            p.getMedical().setNumVisits(result.getInt("num_visits"));

            return p;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // failed
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(Session session, Id<Patient> id)
            throws RemoteException {
        return getAppointmentsForPatient(session, id, null, null);
    }

    private boolean isSessionValid(Session session) {
        return SessionManager.INSTANCE.isValidSession(session);
    }

    @Override
    public Doctor getDoctorById(Session session, Id<Doctor> id) throws RemoteException {
        List<Doctor> doctors = getDoctorsById(session, ImmutableList.of(id));

        if (doctors.size() == 1) {
            return doctors.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Doctor> getDoctorsById(Session session, Iterable<Id<Doctor>> ids)
            throws RemoteException {
        if (!isSessionValid(session)) {
            return Collections.emptyList();
        }

        // Invalid ID check - don't bother running the query

        Iterable<Id<Doctor>> validIds = Iterables.filter(ids, new Predicate<Id<Doctor>>() {
            @Override
            public boolean apply(Id<Doctor> input) {
                return !Id.invalidId().equals(input);
            }
        });

        try {
            Statement sql = getConnection().createStatement();
            ResultSet result = null;

            String sqlStatement = "SELECT * FROM doctor WHERE doctor_id in (";

            String idsJoined = Joiner.on(", ").join(validIds.iterator());
            if (idsJoined.isEmpty()) {
                return Collections.emptyList();
            }
            sqlStatement += idsJoined;
            sqlStatement += ");";

            System.out.println(sqlStatement);
            result = sql.executeQuery(sqlStatement);

            List<Doctor> doctors = Lists.newArrayList();
            while (result.next()) {
                Doctor d = new Doctor();
                d.setDoctor_id(Id.<Doctor> of(result.getInt("doctor_id")));
                d.setName(result.getString("name"));

                doctors.add(d);
            }

            return doctors;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public Patient updatePatientContact(Session session, Id<Patient> id, PatientContact c) {
        if (!isSessionValid(session)) {
            return null;
        }

        if (session.getRole() == UserRole.PATIENT) {
            if (!session.getId().equals(id)) {
                // Patient can only modify their own contact data
                return null;
            }
        }

        try {
            PreparedStatement sql = getConnection().prepareStatement(
                    "UPDATE patient_contact" + " SET address = ?, phone_num = ?"
                            + " WHERE patient_id = ?");
            sql.setString(1, c.getAddress());
            sql.setString(2, c.getPhoneNum());
            sql.setInt(3, id.asInt());

            System.out.println(sql);
            sql.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Doctor> getConsultantsForPatient(Session session, Id<Patient> id)
            throws RemoteException {
        if (!isSessionValid(session)) {
            return Collections.emptyList();
        }

        try {
            String query = "SELECT name FROM patient_consultants NATURAL JOIN doctor"
                    + " WHERE patient_id = ?";

            PreparedStatement sql = getConnection().prepareStatement(query);
            sql.setInt(1, id.asInt());

            System.out.println(sql);
            ResultSet result = sql.executeQuery();

            List<Doctor> consultants = Lists.newArrayList();
            while (result.next()) {
                Doctor d = new Doctor();
                d.setName(result.getString("doctor.name"));
                consultants.add(d);
            }

            return consultants;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public void updatePassword(Session session, UserRole role, Id<?> id, String oldPassword,
            String newPassword) throws RemoteException {

        if (!isSessionValid(session)) {
            return;
        }

        // Permission checks
        EnumSet<UserRole> canChangeOhterPasswords = EnumSet.of(UserRole.ADMIN, UserRole.STAFF);
        boolean hasPermission = canChangeOhterPasswords.contains(session.getRole());
        boolean changingOwnAccount = id.equals(session.getId());
        if (!hasPermission && !changingOwnAccount) {
            // Only certain users can change other account's passwords.
            return;
        }

        try {
            String query = "UPDATE ";

            switch (role) {
                case ACCOUNTANT:
                    query += "financial SET password = ? WHERE finance_id";
                    break;
                case ADMIN:
                    query += "admin SET password = ? WHERE admin_id";
                    break;
                case DOCTOR:
                    query += "doctor SET password = ? WHERE doctor_id";
                    break;
                case LEGAL:
                    query += "legal SET password = ? WHERE lawyer_id";
                    break;
                case PATIENT:
                    query += "patient_contact SET password = ? WHERE patient_id";
                    break;
                case STAFF:
                    query += "staff SET password = ? WHERE staff_id";
                    break;
                default:
                    throw new EnumConstantNotPresentException(UserRole.class, String.valueOf(role));
            }
            query += " = ? AND password = ?;";

            PreparedStatement sql = getConnection().prepareStatement(query);
            sql.setString(1, newPassword);
            sql.setInt(2, id.asInt());
            sql.setString(3, oldPassword);

            System.out.println(sql);
            sql.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin getAdminById(Session session, Id<Admin> id) throws RemoteException {
        if (!isSessionValid(session)) {
            return null;
        }

        // Only admins are allowed to do this operation
        if (session.getRole() != UserRole.ADMIN) {
            return null;
        }

        try {
            String query = "SELECT * FROM admin WHERE admin_id = ?;";

            PreparedStatement sql = getConnection().prepareStatement(query);
            sql.setInt(1, id.asInt());

            System.out.println(sql);
            ResultSet result = sql.executeQuery();

            result.next();

            Admin a = new Admin();
            a.setAdmin_id(id);
            a.setName(result.getString("name"));
            a.setPassword(result.getString("password"));
            return a;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // failed
        return null;
    }

    @Override
    public List<Patient> searchPatients(Session session, PatientSearchOption option, String text)
            throws RemoteException {
        if (!isSessionValid(session)) {
            return Collections.emptyList();
        }

        // Only certain roles can search for patients
        EnumSet<UserRole> canSearchPatients = EnumSet.of(UserRole.ADMIN, UserRole.STAFF,
                UserRole.ACCOUNTANT);
        boolean hasPermission = canSearchPatients.contains(session.getRole());
        if (!hasPermission) {
            return Collections.emptyList();
        }

        // Simple case
        if (option == PatientSearchOption.ID) {
            Patient p = getPatientById(session, Id.<Patient> of(text));
            if (p == null) {
                return Collections.emptyList();
            } else {
                return ImmutableList.of(p);
            }
        }

        try {
            String query = "SELECT * FROM patient_medical NATURAL JOIN patient_contact ";
            PreparedStatement sql;

            if (option == null) {
                sql = getConnection().prepareStatement(query);
            } else {
                switch (option) {
                    case HEALTH_CARD:
                        query += "WHERE health_card_num LIKE ?";
                        sql = getConnection().prepareStatement(query);
                        sql.setString(1, "%" + text + "%");
                        break;
                    case NAME:
                        query += "WHERE name LIKE ?";
                        sql = getConnection().prepareStatement(query);
                        sql.setString(1, "%" + text + "%");
                        break;
                    case SIN:
                        query += "WHERE sin = ?";
                        sql = getConnection().prepareStatement(query);
                        sql.setInt(1, Integer.valueOf(text));
                        break;
                    default:
                        throw new EnumConstantNotPresentException(PatientSearchOption.class,
                                String.valueOf(option));
                }
            }

            ResultSet result = sql.executeQuery();
            List<Patient> patients = Lists.newArrayList();

            while (result.next()) {
                Patient p = new Patient();
                p.setPatientId(Id.<Patient> of(result.getInt("patient_id")));
                p.getContact().setName(result.getString("name"));
                p.getMedical().setSin(result.getInt("sin"));
                p.getMedical().setHealthCardNumber(result.getString("health_card_num"));
                patients.add(p);
            }

            return patients;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public List<Doctor> searchDoctors(Session session, DoctorSearchOption option, String text)
            throws RemoteException {
        if (!isSessionValid(session)) {
            return Collections.emptyList();
        }

        // Only certain roles can search for doctors
        EnumSet<UserRole> canSearchPatients = EnumSet.of(UserRole.ADMIN, UserRole.STAFF,
                UserRole.ACCOUNTANT);
        boolean hasPermission = canSearchPatients.contains(session.getRole());
        if (!hasPermission) {
            return Collections.emptyList();
        }

        // ID
        if (option == DoctorSearchOption.ID) {
            return getDoctorsById(session, ImmutableList.of(Id.<Doctor> of(text)));
        }

        // Name or all
        try {
            String query = "SELECT doctor_id, name FROM doctor";

            if (option == DoctorSearchOption.NAME) {
                query += " WHERE name LIKE ?";
            }

            PreparedStatement sql = getConnection().prepareStatement(query);

            if (option == DoctorSearchOption.NAME) {
                sql.setString(1, "%" + text + "%");
            }

            System.out.println(sql);
            ResultSet result = sql.executeQuery();
            List<Doctor> Doctors = Lists.newArrayList();

            while (result.next()) {
                Doctor p = new Doctor();
                p.setDoctor_id(Id.<Doctor> of(result.getInt("doctor_id")));
                p.setName(result.getString("name"));
                Doctors.add(p);
            }

            return Doctors;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public List<Staff> searchStaff(Session session, StaffSearchOption field, String text)
            throws RemoteException {
        if (!isSessionValid(session)) {
            return Collections.emptyList();
        }

        // Only certain roles can search for staff
        EnumSet<UserRole> canSearchPatients = EnumSet.of(UserRole.ADMIN, UserRole.STAFF);
        boolean hasPermission = canSearchPatients.contains(session.getRole());
        if (!hasPermission) {
            return Collections.emptyList();
        }

        try {
            String query = "SELECT staff_id, name FROM staff";
            PreparedStatement sql;
            if (field == null) {
                // Search-all case
                sql = getConnection().prepareStatement(query);
            } else {
                switch (field) {
                    case ID:
                        query += " WHERE staff_id = ?;";
                        sql = getConnection().prepareStatement(query);
                        sql.setInt(1, Id.of(text).asInt());
                        break;
                    case NAME:
                        query += " WHERE name LIKE ?;";
                        sql = getConnection().prepareStatement(query);
                        sql.setString(1, "%" + text + "%");

                        break;
                    default:
                        throw new EnumConstantNotPresentException(StaffSearchOption.class,
                                String.valueOf(field));
                }
            }

            System.out.println(sql);
            ResultSet result = sql.executeQuery();
            List<Staff> staffs = Lists.newArrayList();

            while (result.next()) {
                Staff s = new Staff();
                s.setStaffId(Id.<Staff> of(result.getInt("staff_id")));
                s.setName(result.getString("name"));
                staffs.add(s);
            }

            return staffs;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public Accountant getAccountantById(Session session, Id<Accountant> id) throws RemoteException {
        if (!isSessionValid(session)) {
            return null;
        }

        // only DB admins or accountant can look up accountants
        if (!ImmutableSet.of(UserRole.ADMIN, UserRole.ACCOUNTANT).contains(session.getRole())) {
            return null;
        }

        // if accountant, can only lookup his own account
        if (session.getRole() == UserRole.ACCOUNTANT && !id.equals(session.getId())) {
            return null;
        }

        try {
            String query = "SELECT * FROM financial WHERE finance_id = ?;";

            PreparedStatement sql = getConnection().prepareStatement(query);
            sql.setInt(1, id.asInt());

            System.out.println(sql);
            ResultSet result = sql.executeQuery();

            result.next();

            Accountant a = new Accountant();
            a.setFinanceId(id);
            a.setName(result.getString("name"));
            a.setPassword(result.getString("password"));
            return a;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // failed
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(Session session, Id<Doctor> doctorId,
            Date start, Date end) {
        if (!isSessionValid(session)) {
            return Collections.emptyList();
        }

        // only certain roles can look up doctor's appointments
        if (!ImmutableSet.of(UserRole.DOCTOR, UserRole.ADMIN, UserRole.ACCOUNTANT).contains(
                session.getRole())) {
            return null;
        }

        try {
            PreparedStatement sql = null;
            String query = "SELECT * FROM appointment NATURAL JOIN patient_contact WHERE doctor_id = ? ";
            String sort = " ORDER BY start_time DESC";

            if (start != null && end != null) {
                // [start, end) range; note that start is inclusive, end is exclusive
                query += "AND start_time BETWEEN ? AND ?";
                query += sort;

                sql = getConnection().prepareStatement(query);
                sql.setInt(1, doctorId.asInt());
                sql.setDate(2, new java.sql.Date(start.getTime()));
                sql.setDate(3, new java.sql.Date(end.getTime()));
            } else if (start != null) {
                // Start is specified, end isn't
                // Start is inclusive
                query += "AND start_time >= ?";
                query += sort;

                sql = getConnection().prepareStatement(query);
                sql.setInt(1, doctorId.asInt());
                sql.setDate(2, new java.sql.Date(start.getTime()));
            } else if (end != null) {
                // Start is not specified, end is specified.
                // end is exclusive.
                query += "AND start_time < ?";
                query += sort;

                sql = getConnection().prepareStatement(query);
                sql.setInt(1, doctorId.asInt());
                sql.setDate(2, new java.sql.Date(end.getTime()));
            } else {
                // Neither specified.
                query += sort;
                sql = getConnection().prepareStatement(query);
                sql.setInt(1, doctorId.asInt());
            }

            System.out.println(sql);
            ResultSet result = sql.executeQuery();

            List<Appointment> apps = Lists.newArrayList();
            while (result.next()) {
                Appointment a = new Appointment();

                a.setStart_time(result.getDate("start_time"));

                // TODO only display the one that is last modified!!
                a.setLast_modified(result.getDate("last_modified"));

                Patient p = new Patient();
                p.getContact().setName(result.getString("name"));
                a.setPatient(p);

                a.setLength(result.getInt("length"));
                a.setProcedures(result.getString("procedures"));
                a.setPrescriptions(result.getString("prescriptions"));
                a.setDiagnoses(result.getString("diagnoses"));

                a.setComment(result.getString("comment"));

                apps.add(a);
            }

            return apps;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(Session session, Id<Patient> patientId,
            Date start, Date end) {
        if (!isSessionValid(session)) {
            return Collections.emptyList();
        }

        // only certain roles can look up patient's appointments
        if (!ImmutableSet
                .of(UserRole.DOCTOR, UserRole.ADMIN, UserRole.ACCOUNTANT, UserRole.PATIENT)
                .contains(session.getRole())) {
            return null;
        }

        try {
            PreparedStatement sql = null;
            String query = "SELECT * FROM appointment NATURAL JOIN doctor WHERE patient_id = ? ";
            String sort = " ORDER BY start_time DESC";

            if (start != null && end != null) {
                // [start, end) range; note that start is inclusive, end is exclusive
                query += "AND start_time BETWEEN ? AND ?";
                query += sort;

                sql = getConnection().prepareStatement(query);
                sql.setInt(1, patientId.asInt());
                sql.setDate(2, new java.sql.Date(start.getTime()));
                sql.setDate(3, new java.sql.Date(end.getTime()));
            } else if (start != null) {
                // Start is specified, end isn't
                // Start is inclusive
                query += "AND start_time >= ?";
                query += sort;

                sql = getConnection().prepareStatement(query);
                sql.setInt(1, patientId.asInt());
                sql.setDate(2, new java.sql.Date(start.getTime()));
            } else if (end != null) {
                // Start is not specified, end is specified.
                // end is exclusive.
                query += "AND start_time < ?";
                query += sort;

                sql = getConnection().prepareStatement(query);
                sql.setInt(1, patientId.asInt());
                sql.setDate(2, new java.sql.Date(end.getTime()));
            } else {
                // Neither specified.
                query += sort;
                sql = getConnection().prepareStatement(query);
                sql.setInt(1, patientId.asInt());
            }

            System.out.println(sql);
            ResultSet result = sql.executeQuery();

            List<Appointment> apps = Lists.newArrayList();
            while (result.next()) {
                Appointment a = new Appointment();

                a.setStart_time(result.getDate("start_time"));

                // TODO only display the one that is last modified!!
                a.setLast_modified(result.getDate("last_modified"));

                Doctor d = new Doctor();
                d.setName(result.getString("name"));
                a.setDoctor(d);

                a.setLength(result.getInt("length"));
                a.setProcedures(result.getString("procedures"));
                a.setPrescriptions(result.getString("prescriptions"));
                a.setDiagnoses(result.getString("diagnoses"));

                // don't show appointment.comment here, unless needed
                apps.add(a);
            }

            return apps;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
