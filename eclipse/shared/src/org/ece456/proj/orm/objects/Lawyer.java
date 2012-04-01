package org.ece456.proj.orm.objects;

import java.io.Serializable;

public class Lawyer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Id<Lawyer> lawyerId;
    private String name;
    private String password;

    public Id<Lawyer> getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(Id<Lawyer> setLawyerId) {
        this.lawyerId = setLawyerId;
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
