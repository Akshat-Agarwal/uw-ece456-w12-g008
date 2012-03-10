package org.ece456.proj.orm.objects;

import java.io.Serializable;

public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    private Id<Admin> admin_id;
    private String name;
    private String password;

    public Id<Admin> getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Id<Admin> admin_id) {
        this.admin_id = admin_id;
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
