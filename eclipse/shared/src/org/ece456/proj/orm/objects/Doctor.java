package org.ece456.proj.orm.objects;

import java.io.Serializable;

public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Id<Doctor> doctor_id;
    private String name;
    private long password_hash;

    public Id<Doctor> getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Id<Doctor> doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(long password_hash) {
        this.password_hash = password_hash;
    }

}
