package org.ece456.proj.orm.objects;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Id<Patient> patient_id;
    private Date start_time;
    private Date last_modified;
    private Id<Doctor> doctor_id;
    private int length;
    private String procedures;
    private String prescriptions;
    private String diagnoses;
    private String comment;

    public Id<Patient> getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Id<Patient> patient_id) {
        this.patient_id = patient_id;
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

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }

    public Id<Doctor> getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Id<Doctor> doctor_id) {
        this.doctor_id = doctor_id;
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
