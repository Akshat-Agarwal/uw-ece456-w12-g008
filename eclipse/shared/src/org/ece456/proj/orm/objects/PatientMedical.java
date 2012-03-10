package org.ece456.proj.orm.objects;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class PatientMedical implements Serializable {

    private static final long serialVersionUID = 1L;

    private int sin;
    private String healthCardNumber;
    private int numVisits;
    private Sex sex;
    private Doctor defaultDoctor;
    private String currentHealth;
    private final List<Doctor> consultants;

    public PatientMedical() {
        consultants = Lists.newArrayList();

        clear();
    }

    public void clear() {
        sin = 0;
        healthCardNumber = "";
        numVisits = 0;
        sex = Sex.MALE;
        defaultDoctor = null;
        currentHealth = "";
        consultants.clear();
    }

    // getters and setters below

    public int getSin() {
        return sin;
    }

    public void setSin(int sin) {
        this.sin = sin;
    }

    public String getHealthCardNumber() {
        return healthCardNumber;
    }

    public void setHealthCardNumber(String healthCardNumber) {
        this.healthCardNumber = healthCardNumber;
    }

    public int getNumVisits() {
        return numVisits;
    }

    public void setNumVisits(int numVisits) {
        this.numVisits = numVisits;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Doctor getDefaultDoctor() {
        return defaultDoctor;
    }

    public void setDefaultDoctor(Doctor defaultDoctor) {
        this.defaultDoctor = defaultDoctor;
    }

    public String getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(String currentHealth) {
        this.currentHealth = currentHealth;
    }

    public List<Doctor> getConsultants() {
        return consultants;
    }

    public void setConsultants(List<Doctor> consultants) {
        this.consultants.clear();
        this.consultants.addAll(consultants);
    }
}