package org.ece456.proj.orm.objects;

import java.io.Serializable;

public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Id<Doctor> doctor_id;
    private String name;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
