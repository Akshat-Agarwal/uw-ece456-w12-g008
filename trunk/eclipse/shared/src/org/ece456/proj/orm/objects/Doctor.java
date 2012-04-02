package org.ece456.proj.orm.objects;

import java.io.Serializable;

import com.google.common.base.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hashCode(doctor_id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Doctor that = (Doctor) obj;
        return Objects.equal(this.doctor_id, that.doctor_id);
    }
}
