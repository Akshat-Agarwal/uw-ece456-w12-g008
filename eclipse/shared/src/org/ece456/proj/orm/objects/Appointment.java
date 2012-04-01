package org.ece456.proj.orm.objects;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    // Do not initialize these; they CAN be null when appropriate.
    private Patient patient;
    private Doctor doctor;

    private Date start_time;
    private Date last_modified;
    private Date time_created;
    private int length;
    private String procedures;
    private String prescriptions;
    private String diagnoses;
    private String comment;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getLast_modified() {
        return last_modified;
    }

    public void setTime_created(Date time_created) {
        this.time_created = time_created;
    }

    public Date getTime_created() {
        return time_created;
    }

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getProcedures() {
        return procedures;
    }

    public void setProcedures(String procedures) {
        this.procedures = procedures;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
