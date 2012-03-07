package org.ece456.proj.orm.objects;

import java.io.Serializable;

public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    private Id<Staff> staffId;
    private String name;
    private long passwordHash;

    public Id<Staff> getStaffId() {
        return staffId;
    }

    public void setStaffId(Id<Staff> staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(long passwordHash) {
        this.passwordHash = passwordHash;
    }
}
